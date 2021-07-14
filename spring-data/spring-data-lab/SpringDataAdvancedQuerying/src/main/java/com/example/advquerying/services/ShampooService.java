package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> findAllShampoosBySizeOrderedById(Size size);

    List<Shampoo> findAllShampoosBySizeOrLabel(Size size, long labelId);

    List<Shampoo> findAllShampoosWithPriceHigherThan(BigDecimal price);

    int findCountOfShampoosWithPriceLowerThan(BigDecimal price);

    List<String> findShampoosByIngredientList(List<String> ingredients);

    List<String> findShampoosByIngredientCount(int count);

}
