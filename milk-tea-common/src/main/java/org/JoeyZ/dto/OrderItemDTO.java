package org.JoeyZ.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long id;
    private Long drinkId;
    private String drinkName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String size;
    private String sugarLevel;
    private String iceLevel;

}
