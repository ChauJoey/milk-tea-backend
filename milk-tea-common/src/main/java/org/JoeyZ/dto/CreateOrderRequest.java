package org.JoeyZ.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private Long userId; // user who order
    private String remark;
    private List<Item> items;

    @Data
    public static class Item {
        private Long drinkId;
        private Integer quantity;
        private String size;
        private String sugarLevel;
        private String iceLevel;
    }
}
