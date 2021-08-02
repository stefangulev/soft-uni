package com.example.demo.services;

import com.example.demo.models.dtos.SaleDto;
import com.example.demo.models.dtos.SaleSeedDto;
import com.example.demo.models.dtos.SaleSingleDto;
import com.example.demo.models.entities.Part;
import com.example.demo.models.entities.Sale;
import com.example.demo.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SaleImpl implements SaleService{

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
        int numberOfSales = ThreadLocalRandom.current().nextInt(10, 30);
        for (int i = 0; i < numberOfSales; i++) {
            SaleSeedDto saleSeedDto = new SaleSeedDto(getRandomDiscount(), carService.getRandom(), customerService.getRandom());
            saleRepository.save(modelMapper.map(saleSeedDto, Sale.class));
        }
    }

    private Double getRandomDiscount() {
        double[] discounts = {0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4 ,0.5};
        int randomDiscountIndex = ThreadLocalRandom.current().nextInt(0, discounts.length);
        return discounts[randomDiscountIndex];
    }
    @Override
    public Long getCount() {
        return saleRepository.count();
    }

    @Override
    public SaleDto getSalesWithAppliedDiscount() {
        List<SaleSingleDto> sales = saleRepository.findAll().stream().map(s -> {
            SaleSingleDto dto = modelMapper.map(s, SaleSingleDto.class);
            setDiscountedPrice(s, dto);
            return dto;

        }).collect(Collectors.toList());

        SaleDto saleDto = new SaleDto();
        saleDto.setSales(sales);
        return saleDto;
    }

    public void setDiscountedPrice(Sale sale, SaleSingleDto saleSingleDto) {
        BigDecimal carPrice = sale.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal::add).orElse(null);
        saleSingleDto.setPriceWithDiscount(carPrice.subtract(carPrice.multiply(BigDecimal.valueOf(sale.getDiscount()))));
    }
}
