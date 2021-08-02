package com.example.jsonprocessingcardealer;

import com.example.jsonprocessingcardealer.models.dtos.PresentCustomerDto;
import com.example.jsonprocessingcardealer.service.*;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandRunner implements CommandLineRunner {
    private final String OUT_PATH = "src/main/resources/files/out/";
    private final String ORDERED_CUSTOMERS_PATH = "ordered-customers.json";
    private final String TOYOTA_CARS_PATH = "toyota-cars.json";
    private final String LOCAL_SUPPLIERS_PATH = "local-suppliers.json";
    private final String CARS_WITH_PARTS = "cars-parts.json";
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final BufferedReader bufferedReader;
    private final Gson gson;

    public CommandRunner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, Gson gson) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.gson = gson;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Enter problem number:");
        int problemNumber = Integer.parseInt(bufferedReader.readLine());
        switch (problemNumber) {
            case 1 : getOrderedCustomers();
            break;
            case 2: getToyotaCars();
            break;
            case 3: getLocalSuppliers();
            break;
            case 4: getCarsAndListOfParts();
        }
        System.out.println("Check 'out' folder in 'resources' to see result");


    }

    private void getCarsAndListOfParts() throws IOException {
        String content = gson.toJson(carService.getCarsAndListOfParts());
        writeToFile(OUT_PATH + CARS_WITH_PARTS , content);
    }

    private void getLocalSuppliers() throws IOException {
        String content = gson.toJson(supplierService.getLocalSuppliers());
        writeToFile(OUT_PATH + LOCAL_SUPPLIERS_PATH, content );
    }

    private void getToyotaCars() throws IOException {
       String content = gson.toJson(carService.getToyotaCarsOrderByModelAndTravelDistance());
       writeToFile(OUT_PATH + TOYOTA_CARS_PATH, content);
    }

    private void getOrderedCustomers() throws IOException {
        List<PresentCustomerDto> allCustomersOrderedByBirthday = customerService.findAllCustomersOrderedByBirthday();
        String content = gson.toJson(allCustomersOrderedByBirthday);System.out.println();
        writeToFile(OUT_PATH + ORDERED_CUSTOMERS_PATH, content);
    }

    private void writeToFile(String path, String content) throws IOException {
        Files.write(Path.of(path), Collections.singleton(content));
    }

    private void seedData() throws IOException {
        supplierService.seedSuppliers();
        partService.seedParts();
        carService.seedCars();
        customerService.seedCustomers();
        saleService.seedSales();

    }
}
