package org.JoeyZ.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDetailDTO {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private Integer status;
    private String remark;
    private LocalDateTime createdTime;

    private List<OrderItemDTO> items;
}
