package org.JoeyZ.service;

import org.JoeyZ.dto.CreateOrderRequest;
import org.JoeyZ.dto.OrderDetailDTO;
import org.JoeyZ.dto.OrderSummaryDTO;
import org.JoeyZ.dto.PageResponse;

import java.util.List;

public interface MtOrderService {
    // clients create order
    long createOrder(CreateOrderRequest request);

    // clients check their all orders
    List<OrderSummaryDTO> listOrdersByUserId(Long userId);

    // clients check their order detail
    OrderDetailDTO getOrderDetailById(Long orderId);

    void updateOrderStatus(Long orderId, Integer toStatus);

    PageResponse<OrderSummaryDTO> listOrdersByUserIdAndPage(Long userId, Integer status, Integer page, Integer pageSize);
}
