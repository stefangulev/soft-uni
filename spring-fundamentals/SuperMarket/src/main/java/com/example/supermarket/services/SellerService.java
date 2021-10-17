package com.example.supermarket.services;

import com.example.supermarket.model.Seller;

public interface SellerService {
    String addSeller(String firstName, String lastName, Integer age, Double salary, String shopName);
    boolean isSellerValid(Seller seller);

}
