package org.JoeyZ.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderSummaryDTO {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private Integer status;
    private String remark;
    private LocalDateTime createdTime;
}
