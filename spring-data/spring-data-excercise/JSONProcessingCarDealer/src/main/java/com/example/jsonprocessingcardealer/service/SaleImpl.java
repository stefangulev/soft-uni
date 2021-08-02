package com.example.jsonprocessingcardealer.service;

import com.example.jsonprocessingcardealer.models.dtos.SaleSeedDto;
import com.example.jsonprocessingcardealer.models.entities.Sale;
import com.example.jsonprocessingcardealer.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
@Service
public class SaleImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final CarService carService;
    private final CustomerService customerService;

    public SaleImpl(SaleRepository saleRepository, ModelMapper modelMapper, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.customerService = customerService;
    }


    @Override
    public void seedSales() {
        if(saleRepository.count() > 0) {
            return;
        }
        int randomSalesCount = ThreadLocalRandom.current().nextInt(10, 20);

        for (int i = 0; i < randomSalesCount; i++) {
            SaleSeedDto saleSeedDto = new SaleSeedDto();
            saleSeedDto.setDiscount(getRandomDiscount());
            saleSeedDto.setCar(carService.getRandom());
            saleSeedDto.setCustomer(customerService.getRandom());
            saleRepository.save(modelMapper.map(saleSeedDto, Sale.class));
        }

    }

    private Double getRandomDiscount() {
        double[] discounts = {0, 5, 10, 15, 20, 30, 40 ,50};
        int randomDiscountIndex = ThreadLocalRandom.current().nextInt(0, discounts.length);
        return discounts[randomDiscountIndex];
    }
}
