package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;

    public IngredientImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public List<Ingredient> findAllIngredientsStartingWith(String pattern) {
       return ingredientRepository.findAllByNameIsStartingWith(pattern);
    }

    @Override
    public List<Ingredient> findAllIngredientsFromListAndOrderByPrinceAsc(List<String> list) {
        return ingredientRepository.findAllByNameIsInOrderByPriceAsc(list);
    }

    @Override
    @Transactional
    public int deleteByName(String name) {
        return ingredientRepository.deleteByName(name);
    }
    @Override
    @Transactional
    public int updateBy10Percent() {
        return ingredientRepository.updateBy10Percent();
    }

    @Override
    @Transactional
    public int updateByGivenPercentageWhereIngredientInList(double percentage, List<String> names) {
        double convertPercentage = percentage/100 + 1;
        BigDecimal actualPercentage = new BigDecimal(convertPercentage);
        return ingredientRepository.updateByGivenPercentageWhereIngredientInList(actualPercentage, names);
    }
}
