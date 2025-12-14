package org.JoeyZ.service.impl;

import lombok.RequiredArgsConstructor;
import org.JoeyZ.ErrorCode;
import org.JoeyZ.MtException;
import org.JoeyZ.dto.*;
import org.JoeyZ.entity.MtDrink;
import org.JoeyZ.entity.MtOrder;
import org.JoeyZ.entity.MtOrderItem;
import org.JoeyZ.enums.OrderStatus;
import org.JoeyZ.mapper.DrinkMapper;
import org.JoeyZ.mapper.OrderItemMapper;
import org.JoeyZ.mapper.OrderMapper;
import org.JoeyZ.service.MtOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MtOrderServiceImpl implements MtOrderService {

    private final OrderMapper orderMapper;

    private final OrderItemMapper orderItemMapper;

    private final DrinkMapper drinkMapper;

    /**
     * create order:
     * 1.according to request, calculate total price
     * 2.insert mt_order first
     * 3.insert mt_order_item
     * 4.return order id
     *
     * @param request
     * @return order id
     */
    @Override
    @Transactional
    public long createOrder(CreateOrderRequest request) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<MtOrderItem> items = new ArrayList<>();
        for (CreateOrderRequest.Item item : request.getItems()) {
            MtDrink drink = drinkMapper.getDrinkById(item.getDrinkId());
            // check drink exist
            if (drink == null) {
                throw new MtException(ErrorCode.NOT_FOUND, "Drink not found");
            }
            // unit price of milk tea
            BigDecimal unitPrice = drink.getPrice();

            // batch price of same milk tea
            BigDecimal itemTotalPrice = unitPrice.multiply(new BigDecimal(item.getQuantity()));
            totalPrice = totalPrice.add(itemTotalPrice);

            // create order item
            MtOrderItem orderItem = new MtOrderItem();
            orderItem.setDrinkId(item.getDrinkId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(unitPrice);

            // customize
            orderItem.setSize(item.getSize());
            orderItem.setSugarLevel(item.getSugarLevel());
            orderItem.setIceLevel(item.getIceLevel());

            // create order item
            items.add(orderItem);
        }

        // 2. create order, link user id and order id. insert mt_order
        MtOrder order = new MtOrder();
        order.setUserId(request.getUserId());
        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setRemark(request.getRemark());

        // insert order
        orderMapper.createOrder(order);

        // 3. insert mt_order_item
        for (MtOrderItem item : items) {
            item.setOrderId(order.getId());
            orderItemMapper.insertOrderItem(item);
        }

        return order.getId();
    }

    /**
     * list orders by user id :/orders?userId=xxx
     *
     * @param userId
     * @return
     */
    @Override
    public List<OrderSummaryDTO> listOrdersByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is null");
        }

        List<MtOrder> orders = orderMapper.getOrdersByUserId(userId);
        List<OrderSummaryDTO> orderSummaryDTOList = new ArrayList<>();

        for (MtOrder order : orders) {
            OrderSummaryDTO dto = new OrderSummaryDTO();
            dto.setId(order.getId());
            dto.setUserId(order.getUserId());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setStatus(order.getStatus());
            dto.setRemark(order.getRemark());
            dto.setCreatedTime(order.getCreateTime());
            orderSummaryDTOList.add(dto);
        }
        return orderSummaryDTOList;
    }

    /**
     * order detail: /orders/{orderId}
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderDetailDTO getOrderDetailById(Long orderId) {
        if (orderId == null) {
            throw new MtException(ErrorCode.PARAMETER_ERROR, "orderId is null");
        }

        MtOrder order = orderMapper.getOrderById(orderId);
        if (order == null) {
            throw new MtException(ErrorCode.NOT_FOUND, "Order not found");
        }

        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        dto.setRemark(order.getRemark());
        dto.setCreatedTime(order.getCreateTime());

        List<OrderItemDTO> items = orderItemMapper.getItemsByOrderId(orderId);
        dto.setItems(items);
        return dto;
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long orderId, Integer toStatus) {
        if (orderId == null)
            throw new MtException(ErrorCode.PARAMETER_ERROR, "orderId is null");

        // from pending to pending check
        OrderStatus target = OrderStatus.fromCode(toStatus);
        if (target == OrderStatus.PENDING)
            throw new MtException(ErrorCode.PARAMETER_ERROR, "Invalid target status: " + target.getDescription());

        // check order exist
        MtOrder order = orderMapper.getOrderById(orderId);
        if (order == null)
            throw new MtException(ErrorCode.NOT_FOUND, "Order not found" + orderId);

        // check current status: pending -> completed | cancelled
        OrderStatus currentStatus = OrderStatus.fromCode(order.getStatus());
        if (currentStatus != OrderStatus.PENDING)
            throw new MtException(ErrorCode.PARAMETER_ERROR, "Invalid current status: " + currentStatus.getDescription());

        int update = orderMapper.updateOrderStatus(orderId, currentStatus.getCode(), target.getCode());
        if (update != 1)
            throw new MtException(ErrorCode.SERVER_ERROR, "Failed to update status (maybe already changed).");

    }

    /**
     * list orders by user id and page & page size
     * @param userId
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResponse<OrderSummaryDTO> listOrdersByUserIdAndPage(Long userId, Integer status, Integer page, Integer pageSize) {
        if (userId == null)
            throw new IllegalArgumentException("userId is null");

        int p = (page == null || page < 1)? 1: page;
        int ps = (pageSize == null || pageSize < 1) ? 10 : pageSize;
        if (ps > 100) ps = 100;

        int offset = (p - 1) * ps;

        // count total orders if not result return empty
        Long total = orderMapper.countOrdersByUserIdAndStatus(userId, status);
        if (total == null || total == 0) {
            return new PageResponse<>(0, p, ps, java.util.Collections.emptyList());
        }

        // get orders by user id and page
        List<MtOrder> orders = orderMapper.getOrdersByUserIdAndPage(userId, status, offset, ps);
        List<OrderSummaryDTO> orderSummaryDTOList = new ArrayList<>();

        for (MtOrder order : orders) {
            OrderSummaryDTO dto = new OrderSummaryDTO();
            dto.setId(order.getId());
            dto.setUserId(order.getUserId());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setStatus(order.getStatus());
            dto.setRemark(order.getRemark());
            dto.setCreatedTime(order.getCreateTime());
            orderSummaryDTOList.add(dto);
        }

        return new PageResponse<>(total, p, ps, orderSummaryDTOList);
    }
}
