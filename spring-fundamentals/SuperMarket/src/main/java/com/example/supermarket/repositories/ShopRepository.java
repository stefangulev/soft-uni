package com.example.supermarket.repositories;

import com.example.supermarket.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findShopByName(String name);
}
