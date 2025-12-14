package org.JoeyZ.mapper;

import org.JoeyZ.entity.MtOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int createOrder(@Param("order") MtOrder order);

    MtOrder getOrderById(@Param("id") Long id);

    List<MtOrder> getOrdersByUserId(@Param("userId") Long userId);

    int updateOrderStatus(@Param("id") Long id, @Param("fromStatus") Integer fromStatus, @Param("toStatus") Integer toStatus);

    List<MtOrder> getOrdersByUserIdAndPage(@Param("userId") Long userId, @Param("status") Integer status, @Param("offset") int offset, @Param("limit") int limit);

    Long countOrdersByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Integer status);
}
