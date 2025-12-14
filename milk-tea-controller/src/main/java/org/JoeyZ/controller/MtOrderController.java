package org.JoeyZ.controller;

import lombok.RequiredArgsConstructor;
import org.JoeyZ.ApiResponse;
import org.JoeyZ.dto.*;
import org.JoeyZ.service.MtOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class MtOrderController {
    private final MtOrderService mtOrderService;

    @PostMapping
    public ApiResponse<Long> createOrder(@RequestBody CreateOrderRequest request) {
        return ApiResponse.success(mtOrderService.createOrder(request));
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderDetailDTO> getOrderById(@PathVariable Long orderId) {
        return ApiResponse.success(mtOrderService.getOrderDetailById(orderId));
    }

    @GetMapping
    public ApiResponse<List<OrderSummaryDTO>> listOrdersByUserId(@RequestParam Long userId) {
        return ApiResponse.success(mtOrderService.listOrdersByUserId(userId));
    }

    @PatchMapping("/{orderId}/status")
    public ApiResponse<Void> updateOrderStatus(@PathVariable Long orderId, @RequestBody UpdateOrderStatusRequest request) {
        mtOrderService.updateOrderStatus(orderId, request.getStatus());
        return ApiResponse.success(null);
    }

    @GetMapping("/page")
    public ApiResponse<PageResponse<OrderSummaryDTO>> listOrdersByUserIdAndPage(@RequestParam Long userId,
                                                                                @RequestParam(required = false) Integer status,
                                                                                @RequestParam(required = false) Integer page,
                                                                                @RequestParam(required = false) Integer pageSize) {
        return ApiResponse.success(mtOrderService.listOrdersByUserIdAndPage(userId, status, page, pageSize));
    }
}
