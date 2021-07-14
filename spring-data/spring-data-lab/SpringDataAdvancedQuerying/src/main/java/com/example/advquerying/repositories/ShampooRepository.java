package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllBySizeOrderById(Size size);
    List<Shampoo> findAllBySizeOrLabel_IdOrderByPriceAsc(Size size, long labelId);
    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    List<Shampoo> findAllByPriceLessThan(BigDecimal price);

    @Query("SELECT DISTINCT s.brand FROM Shampoo s JOIN s.ingredients i WHERE i.name IN :ingredients")
    List<String> getShampoosByIngredientsList(List<String> ingredients);

    @Query("SELECT s.brand FROM Shampoo s WHERE s.ingredients.size < :count")
    List<String> getShampooBrandsWhereIngredientCountIs(int count);


}
