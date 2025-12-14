package org.JoeyZ.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MtUser {
    private Long id;
    private String username;
    private String phone;
    private String password;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime  updateTime;
}
