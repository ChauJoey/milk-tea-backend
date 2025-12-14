package org.JoeyZ.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MtOrderItem {
    private Long id;
    private Long orderId;
    private Long drinkId;
    private Integer quantity;
    private BigDecimal unitPrice;

    private String size;
    private String sugarLevel;
    private String iceLevel;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
