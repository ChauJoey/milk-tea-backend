package org.JoeyZ.controller;


import org.JoeyZ.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/welcome")
    public ApiResponse<String> Welcome() {
        return new ApiResponse<>(1,"ok","Welcome to the milk tea ordering system!");
    }
}
