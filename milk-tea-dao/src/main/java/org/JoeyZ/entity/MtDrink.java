package org.JoeyZ.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MtDrink {
    private Long id;
    private String name;
    private BigDecimal price;

    private String size; // small / medium / large
    private String sugarLevel; // normal / less / no
    private String iceLevel; // normal / less / no

    private Integer status; // 1=normal, 0=disabled

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
