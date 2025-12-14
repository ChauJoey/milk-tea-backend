package org.JoeyZ.service;

import org.JoeyZ.entity.MtDrink;

import java.util.List;

public interface MtDrinkService {
    MtDrink getDrinkById(Long id);

    List<MtDrink> listAllDrinks();

    int createDrink(MtDrink drink);

    int updateDrink(MtDrink drink);

    int deleteDrink(Long id);
}
