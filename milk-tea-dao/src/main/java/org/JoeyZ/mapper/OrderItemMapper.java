package org.JoeyZ.mapper;

import org.JoeyZ.dto.OrderItemDTO;
import org.JoeyZ.entity.MtOrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    int insertOrderItem(MtOrderItem orderItem);

    int insertOrderItemBatch(List<MtOrderItem> orderItems);

    List<OrderItemDTO> getItemsByOrderId(Long orderId);
}
