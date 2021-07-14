package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class ShampooImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findAllShampoosBySizeOrderedById(Size size) {
        return shampooRepository.findAllBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> findAllShampoosBySizeOrLabel(Size size, long labelId) {
        return shampooRepository.findAllBySizeOrLabel_IdOrderByPriceAsc(size, labelId);
    }

    @Override
    public List<Shampoo> findAllShampoosWithPriceHigherThan(BigDecimal price) {
        return shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int findCountOfShampoosWithPriceLowerThan(BigDecimal price) {
        return shampooRepository.findAllByPriceLessThan(price).size();
    }

    @Override
    public List<String> findShampoosByIngredientList(List<String> ingredients) {
       return shampooRepository.getShampoosByIngredientsList(ingredients);
    }

    @Override
    public List<String> findShampoosByIngredientCount(int count) {
        return shampooRepository.getShampooBrandsWhereIngredientCountIs(count);
    }
}
