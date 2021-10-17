package com.example.supermarket.services;

import com.example.supermarket.model.Shop;

public interface ShopService {
    String addShop(String name, String address, String town);
    boolean isShopValid(Shop shop);
}
