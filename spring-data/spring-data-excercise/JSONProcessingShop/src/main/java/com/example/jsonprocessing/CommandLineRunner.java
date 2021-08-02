package com.example.jsonprocessing;

import com.example.jsonprocessing.models.dtos.CategoryStatsDto;
import com.example.jsonprocessing.models.dtos.ProductWithoutBuyerDto;
import com.example.jsonprocessing.models.dtos.UserCatalogueDto;
import com.example.jsonprocessing.models.dtos.UserWithMoreThanOneSoldItemDto;
import com.example.jsonprocessing.services.CategoryService;
import com.example.jsonprocessing.services.ProductService;
import com.example.jsonprocessing.services.UserService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private static final String OUTPUT_PATH = "src/main/resources/files/out/";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.json";
    private static final String STATS_PER_CATEGORY = "stats-per-category.json";
    private static final String USERS_WITH_MORE_THAN_ONE_SOLD = "users-with-more-than-one-sold.json";
    private static final String CATALOGUE_USERS_ITEMS = "catalogue-users-items.json";
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;
    private final Gson gson;

    public CommandLineRunner(UserService userService, CategoryService categoryService, ProductService productService, Gson gson) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.gson = gson;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws IOException {
        seedData();
        System.out.println("Enter problem number:");
        int problemNumber=  Integer.parseInt(bufferedReader.readLine());

        switch (problemNumber) {
            case 1: productsInRange();
            break;
            case 2: usersWithMoreThanOneSoldItem();
            break;
            case 3: categoriesByProductsCount();
            break;
            case 4: getCatalogueOfUsersAndSoldProducts();
            break;
        }
        System.out.println("Check 'out' folder in 'resources' to see result");

    }

    private void getCatalogueOfUsersAndSoldProducts() throws IOException {
        List<UserCatalogueDto> catalogueOfUsersAndSoldProducts = userService.getCatalogueOfUsersAndSoldProducts();
        String content = gson.toJson(catalogueOfUsersAndSoldProducts);
        writeToFile(OUTPUT_PATH + CATALOGUE_USERS_ITEMS, content);
    }

    private void categoriesByProductsCount() throws IOException {
        List<CategoryStatsDto> statsPerCategories = categoryService.getStatsPerCategories();
        String content = gson.toJson(statsPerCategories);
        writeToFile(OUTPUT_PATH + STATS_PER_CATEGORY, content);
    }

    private void productsInRange() throws IOException {
        List<ProductWithoutBuyerDto> productsWithoutBuyersInRange = productService.getProductsWithoutBuyersInRange(new BigDecimal(500), new BigDecimal(1000));
        String content = gson.toJson(productsWithoutBuyersInRange);
        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE_FILE_NAME, content);
    }
    private void usersWithMoreThanOneSoldItem() throws IOException {
        List<UserWithMoreThanOneSoldItemDto> usersWithMoreThanOneSoldProductOrderByLastNameThenFirstName = userService.findUsersWithMoreThanOneSoldProductOrderByLastNameThenFirstName();
        String content = gson.toJson(usersWithMoreThanOneSoldProductOrderByLastNameThenFirstName);
        writeToFile(OUTPUT_PATH + USERS_WITH_MORE_THAN_ONE_SOLD, content);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    public void seedData() throws IOException {
        userService.seedUsers();
        categoryService.seedCategories();
        productService.seedProducts();

    }
}
