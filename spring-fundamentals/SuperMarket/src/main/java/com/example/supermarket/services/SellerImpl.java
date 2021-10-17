package com.example.supermarket.services;

import com.example.supermarket.model.Seller;
import com.example.supermarket.repositories.SellerRepository;
import com.example.supermarket.repositories.ShopRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
@Service
public class SellerImpl implements SellerService{
    private final SellerRepository sellerRepository;
    private final ShopRepository shopRepository;
    private final Validator validator;

    public SellerImpl(SellerRepository sellerRepository, ShopRepository shopRepository, Validator validator) {
        this.sellerRepository = sellerRepository;
        this.shopRepository = shopRepository;
        this.validator = validator;
    }

    @Override
    public String addSeller(String firstName, String lastName, Integer age, Double salary, String shopName) {
        Seller seller = new Seller(firstName, lastName, age, salary);
            seller.setShop(shopRepository.findShopByName(shopName));
            if (!isSellerValid(seller)) {
                return "Invalid seller!";
            }
            sellerRepository.save(seller);
            return "Successfully added seller!";
    }

    @Override
    public boolean isSellerValid(Seller seller) {
        return validator.validate(seller).isEmpty();
    }
}
