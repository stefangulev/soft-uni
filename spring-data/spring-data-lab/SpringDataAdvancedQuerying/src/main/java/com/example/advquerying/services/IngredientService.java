package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<Ingredient> findAllIngredientsStartingWith(String pattern);

    List<Ingredient> findAllIngredientsFromListAndOrderByPrinceAsc(List<String> list);
    int deleteByName(String name);
    int updateBy10Percent();
  int updateByGivenPercentageWhereIngredientInList(double percentage, List<String> names);
}
