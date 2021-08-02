package com.example.demo;

import com.example.demo.models.dtos.*;
import com.example.demo.services.*;
import com.example.demo.util.XmlConverter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Service
public class ConsoleRunner implements CommandLineRunner {
    private final String INPUT_FILE_PATH = "src/main/resources/files/";
    private final String SUPPLIER_INPUT_FILE = "suppliers.xml";
    private final String PART_INPUT_FILE = "parts.xml";
    private final String CAR_INPUT_FILE = "cars.xml";
    private final String CUSTOMER_INPUT_FILE = "customers.xml";
    private final String OUTPUT_PATH = "src/main/resources/files/out/";
    private final String ORDERED_CUSTOMER_FILE = "ordered-customers.xml";
    private final String CARS_BY_MAKE_FILE = "cars-by-make.xml";
    private final String LOCAL_SUPPLIERS_FILE = "local-suppliers.xml";
    private final String CARS_WITH_PARTS = "cars-with-parts.xml";
    private final String TOTAL_SALES = "total-sales.xml";
    private final String SALES_WITH_DISCOUNT_APPLIED = "sales-with-discount-applied.xml";
    private final ModelMapper modelMapper;
    private final XmlConverter xmlConverter;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final BufferedReader bufferedReader;

    public ConsoleRunner(SupplierService supplierService, ModelMapper modelMapper, XmlConverter xmlConverter, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.modelMapper = modelMapper;
        this.xmlConverter = xmlConverter;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Enter problem number:");
        int problemNumber = Integer.parseInt(bufferedReader.readLine());
        switch (problemNumber) {
            case 1: orderedCustomer();
            break;
            case 2: carsFromToyota();
            break;
            case 3: localSuppliers();
            break;
            case 4: carsWithListOfParts();
            break;
            case 5: totalSalesByCustomer();
            break;
            case 6: salesWithAppliedDiscount();
        }

    }

    private void salesWithAppliedDiscount() throws JAXBException {
        SaleDto salesWithAppliedDiscount = saleService.getSalesWithAppliedDiscount();
        xmlConverter.toFile(OUTPUT_PATH + SALES_WITH_DISCOUNT_APPLIED, salesWithAppliedDiscount);
    }

    private void totalSalesByCustomer() throws JAXBException {
        TotalSalesByCustomerDto totalSalesByCustomer = customerService.getTotalSalesByCustomer();
        xmlConverter.toFile(OUTPUT_PATH + TOTAL_SALES, totalSalesByCustomer);
    }

    private void carsWithListOfParts() throws JAXBException {
        CarsWithPartsDto carsWithListOfParts = carService.findCarsWithListOfParts();
        xmlConverter.toFile(OUTPUT_PATH + CARS_WITH_PARTS, carsWithListOfParts);
    }

    private void localSuppliers() throws JAXBException {
        LocalSupplierDto localSuppliers = supplierService.findLocalSuppliers();
        xmlConverter.toFile(OUTPUT_PATH + LOCAL_SUPPLIERS_FILE, localSuppliers);
    }

    private void carsFromToyota() throws JAXBException {
        CarsByMakeDto toyotaCarsOrderedByModelAndTravelledDistance = carService.getToyotaCarsOrderedByModelAndTravelledDistance();
        xmlConverter.toFile(OUTPUT_PATH + CARS_BY_MAKE_FILE, toyotaCarsOrderedByModelAndTravelledDistance);
    }

    private void orderedCustomer() throws JAXBException {
        OrderedCustomerDto orderedCustomersByBirthDate = customerService.getOrderedCustomersByBirthDate();
        xmlConverter.toFile(OUTPUT_PATH + ORDERED_CUSTOMER_FILE, orderedCustomersByBirthDate);
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        if(supplierService.getCount() == 0) {
            SupplierSeedDto supplierSeedDto = xmlConverter.fromFile(INPUT_FILE_PATH + SUPPLIER_INPUT_FILE, SupplierSeedDto.class);
            supplierService.seedSuppliers(supplierSeedDto);
        }
        if (partService.getCount() == 0) {
            PartSeedDto partSeedDto = xmlConverter.fromFile(INPUT_FILE_PATH + PART_INPUT_FILE, PartSeedDto.class);
            partService.seedParts(partSeedDto);
        }
        if (carService.getCount() == 0) {
            CarSeedDto carSeedDto = xmlConverter.fromFile(INPUT_FILE_PATH + CAR_INPUT_FILE, CarSeedDto.class);
            carService.seedCars(carSeedDto);
        }
        if (customerService.getCount() == 0) {
            CustomerSeedDto customerSeedDto = xmlConverter.fromFile(INPUT_FILE_PATH + CUSTOMER_INPUT_FILE, CustomerSeedDto.class);
            customerService.seedCustomers(customerSeedDto);
        }
        if (saleService.getCount() == 0) {
            saleService.seedSales();
        }
    }
}
