package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameIsStartingWith(String pattern);
    List<Ingredient> findAllByNameIsInOrderByPriceAsc(List<String> list);
    @Query("DELETE FROM Ingredient i WHERE i.name = :name")
    @Modifying
    int deleteByName(String name);

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.10")
    @Modifying
    int updateBy10Percent();

    @Query("UPDATE Ingredient i SET i.price = i.price * :priceChange WHERE i.name IN :ingredientList")
    @Modifying
    int updateByGivenPercentageWhereIngredientInList(BigDecimal priceChange, List<String> ingredientList);
}
