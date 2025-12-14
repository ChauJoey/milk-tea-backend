package org.JoeyZ.controller;

import lombok.RequiredArgsConstructor;
import org.JoeyZ.service.MtUserService;
import org.JoeyZ.entity.MtUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MtUserController {

    private final MtUserService mtUserService;

    @GetMapping("/{id}")
    public MtUser getUserById(@PathVariable Long id) {
        return mtUserService.getUserById(id);
    }
}
