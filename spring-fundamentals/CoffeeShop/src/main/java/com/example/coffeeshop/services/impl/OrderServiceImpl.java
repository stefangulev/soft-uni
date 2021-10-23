package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.model.entities.Order;
import com.example.coffeeshop.model.service.OrderAddServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.repositories.OrderRepository;
import com.example.coffeeshop.services.CategoryService;
import com.example.coffeeshop.services.OrderService;
import com.example.coffeeshop.services.UserService;
import com.example.coffeeshop.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }

    @Override
    public void addOffer(OrderAddServiceModel orderAddServiceModel) {
        Order order = modelMapper.map(orderAddServiceModel, Order.class);
        order.setEmployee(userService.findUserById(currentUser.getId()));
        order.setCategory(categoryService.findCategoryByName(orderAddServiceModel.getCategory()));
        orderRepository.save(order);
    }

    @Override
    public List<OrderViewModel> findAllOrdersInDescOrder() {
        return orderRepository.findAllByOrderByPriceDesc().stream()
                .map(o -> modelMapper.map(o, OrderViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void readyOffer(Long id) {
        orderRepository.deleteById(id);
    }
}
