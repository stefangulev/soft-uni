package com.example.xmlprocessingshop;

import com.example.xmlprocessingshop.models.dtos.*;
import com.example.xmlprocessingshop.services.CategoryService;
import com.example.xmlprocessingshop.services.ProductService;
import com.example.xmlprocessingshop.services.UserService;
import com.example.xmlprocessingshop.util.XMLConvertor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ConsoleRunner implements CommandLineRunner {
    private final String FILE_PATH = "src/main/resources/files/";
    private final String USERS_FILE = "users.xml";
    private final String CATEGORIES_FILE = "categories.xml";
    private final String PRODUCTS_FILE = "products.xml";
    private final String OUTPUT_FILE_PATH = "src/main/resources/files/out/";
    private final String PRODUCTS_IN_RANGE_FILE = "products-in-range.xml";
    private final String USERS_WITH_ATLEAST_ONE_SOLD = "users-with-atleast-one-sold.xml";
    private final String CATEGORIES_BY_PRODUCT_COUNT = "categories-by-product-count.xml";
    private final String USERS_AND_PRODUCTS = "users-and-products.xml";
    private final XMLConvertor xmlConvertor;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;

    public ConsoleRunner(XMLConvertor xmlConvertor, UserService userService, CategoryService categoryService, ProductService productService) {
        this.xmlConvertor = xmlConvertor;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Enter problem number:");
        try {
            int problemNum = Integer.parseInt(bufferedReader.readLine());
            switch (problemNum) {
                case 1:
                    productsInRange();
                    break;
                case 2:
                    successfullySoldProducts();
                    break;
                case 3:
                    categoriesByProductCount();
                    break;
                case 4:
                    usersAndProducts();
                    break;
                default:
                    System.out.println("No such problem exists.");
            }
        } catch (Exception ex) {
            System.out.println("Invalid input format.");
        }


    }

    private void usersAndProducts() throws JAXBException, IOException {
        UsersAndProductsDto usersAndProducts = userService.getUsersAndProducts();
        xmlConvertor.toFile(OUTPUT_FILE_PATH + USERS_AND_PRODUCTS, usersAndProducts);

    }

    private void categoriesByProductCount() throws JAXBException, IOException {
        CategoryByProductCountDto categoriesByProductCount = categoryService.getCategoriesByProductCount();
        xmlConvertor.toFile(OUTPUT_FILE_PATH + CATEGORIES_BY_PRODUCT_COUNT, categoriesByProductCount);
    }

    private void successfullySoldProducts() throws JAXBException, IOException {
        UsersWithAtLeastOneSoldDto allUsersWithAtLeastOneSoldProduct = userService.getAllUsersWithAtLeastOneSoldProduct();
        xmlConvertor.toFile(OUTPUT_FILE_PATH + USERS_WITH_ATLEAST_ONE_SOLD, allUsersWithAtLeastOneSoldProduct);
    }

    private void productsInRange() throws JAXBException, IOException {
        ProductsInRangeDto productsInRange = productService.getProductsInRange();
        xmlConvertor.toFile(OUTPUT_FILE_PATH + PRODUCTS_IN_RANGE_FILE, productsInRange);
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        if (userService.getCount() == 0) {
            UserSeedDto userSeedDto = xmlConvertor.fromFile(FILE_PATH + USERS_FILE, UserSeedDto.class);
            userService.seedUsers(userSeedDto);
        }
        if (categoryService.getCount() == 0) {
            CategorySeedDto categorySeedDto = xmlConvertor.fromFile(FILE_PATH + CATEGORIES_FILE, CategorySeedDto.class);
            categoryService.seedCategories(categorySeedDto);
        }
        if (productService.getCount() == 0) {
            ProductSeedDto productSeedDto = xmlConvertor.fromFile(FILE_PATH + PRODUCTS_FILE, ProductSeedDto.class);
            productService.seedProducts(productSeedDto);
        }
    }
}
