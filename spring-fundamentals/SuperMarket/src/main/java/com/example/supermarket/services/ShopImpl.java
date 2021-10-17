package com.example.supermarket.services;

import com.example.supermarket.model.Shop;
import com.example.supermarket.repositories.ShopRepository;
import com.example.supermarket.repositories.TownRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
@Service
public class ShopImpl implements ShopService{
    private final ShopRepository shopRepository;
    private final TownRepository townRepository;
    private final Validator validator;

    public ShopImpl(ShopRepository shopRepository, TownRepository townRepository, Validator validator) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.validator = validator;
    }

    @Override
    public String addShop(String name, String address, String town) {
        Shop shop = new Shop(name, address);
        shop.setTown(townRepository.findTownByName(town));
        if (!isShopValid(shop)) {
            return "Invalid shop";
        }
        shopRepository.save(shop);
        return "Successfully added shop!";
    }

    @Override
    public boolean isShopValid(Shop shop) {
        return validator.validate(shop).isEmpty();
    }
}
