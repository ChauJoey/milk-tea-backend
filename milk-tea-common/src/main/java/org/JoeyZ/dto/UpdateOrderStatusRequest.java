package org.JoeyZ.dto;

public class UpdateOrderStatusRequest {
    private Integer status; // 1: completed, 2: cancelled

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
