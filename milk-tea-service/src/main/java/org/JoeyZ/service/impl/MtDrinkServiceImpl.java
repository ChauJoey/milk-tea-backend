package org.JoeyZ.service.impl;

import lombok.RequiredArgsConstructor;
import org.JoeyZ.entity.MtDrink;
import org.JoeyZ.mapper.DrinkMapper;
import org.JoeyZ.service.MtDrinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MtDrinkServiceImpl implements MtDrinkService {

    private final DrinkMapper drinkMapper;
    @Override
    public MtDrink getDrinkById(Long id) {
        return drinkMapper.getDrinkById(id);
    }

    @Override
    public List<MtDrink> listAllDrinks() {
        return drinkMapper.listAllDrinks();
    }

    @Override
    public int createDrink(MtDrink drink) {
        return drinkMapper.createDrink(drink);
    }

    @Override
    public int updateDrink(MtDrink drink) {
        return drinkMapper.updateDrink(drink);
    }

    @Override
    public int deleteDrink(Long id) {
        return drinkMapper.deleteDrink(id);
    }
}
