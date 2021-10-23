package com.example.coffeeshop.services;

import com.example.coffeeshop.model.service.OrderAddServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void addOffer(OrderAddServiceModel orderAddServiceModel);
    List<OrderViewModel> findAllOrdersInDescOrder();
    void readyOffer(Long id);
}
