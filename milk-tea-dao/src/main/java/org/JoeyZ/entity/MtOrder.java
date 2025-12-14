package org.JoeyZ.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MtOrder {
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private Integer status; // 0=pending, 1=completed, 2=cancelled
    private String remark;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
