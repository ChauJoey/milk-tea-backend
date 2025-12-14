package org.JoeyZ.controller;

import lombok.RequiredArgsConstructor;
import org.JoeyZ.entity.MtDrink;
import org.JoeyZ.service.MtDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drinks")
@RequiredArgsConstructor
public class MtDrinkController {

    private final MtDrinkService mtDrinkService;

    @GetMapping("/{id}")
    public MtDrink getDrinkById(@PathVariable Long id) {
        return mtDrinkService.getDrinkById(id);
    }

    @GetMapping
    public List<MtDrink> listAllDrinks() {
        return mtDrinkService.listAllDrinks();
    }

    @PostMapping
    public int createDrink(MtDrink drink) {
        return mtDrinkService.createDrink(drink);
    }

    @DeleteMapping
    public int deleteDrink(@PathVariable Long id) {
        return mtDrinkService.deleteDrink(id);
    }

    @PutMapping
    public int updateDrink(MtDrink drink) {
        return mtDrinkService.updateDrink(drink);
    }
}
