package org.JoeyZ.mapper;

import org.JoeyZ.entity.MtDrink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DrinkMapper {
    // search all List drinks
    List<MtDrink> listAllDrinks();

    MtDrink getDrinkById(Long id);

    int createDrink(MtDrink drink);

    int updateDrink(MtDrink drink);

    int deleteDrink(Long id);

}
