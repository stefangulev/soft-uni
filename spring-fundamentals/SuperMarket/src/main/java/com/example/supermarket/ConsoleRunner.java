package com.example.supermarket;

import com.example.supermarket.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Service
public class ConsoleRunner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final TownService townService;
    private final ShopService shopService;
    private final SellerService sellerService;
    private final ProductService productService;
    private final Scanner scanner;

    public ConsoleRunner(CategoryService categoryService, TownService townService, ShopService shopService, SellerService sellerService, ProductService productService) {
        this.categoryService = categoryService;
        this.townService = townService;
        this.shopService = shopService;
        this.sellerService = sellerService;
        this.productService = productService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hi");
        while (true) {
            System.out.println("Choose option from:");
            System.out.println("1 - for Add Category\n2 - for Add Town\n3 - for Add Shop\n4 - for Add Seller\n5- for Add Product" +
                    "\n6 - for Set Seller new manager\n7 - for Destributing product in shops\n8 - for Showing all sellers in Shop" +
                    "\n9 - for Showing all products in Shop");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println(addCategory());
                    break;
                case "2":
                    System.out.println(addTown());
                    break;
                case "3":
                    System.out.println(addShop());
                    break;
                case "4":
                    System.out.println(addSeller());
                    break;
                case "5":
                    System.out.println(addProduct());
                    break;

                default:
                    System.out.println("Invalid task number");


            }


        }
    }

    public String addCategory() {
        System.out.println("Enter category name:");
        String categoryName = scanner.nextLine();
        return categoryService.addCategory(categoryName);
    }

    public String addTown() {
        System.out.println("Enter town name:");
        String townName = scanner.nextLine();
        return townService.addTown(townName);
    }

    public String addShop() {
        System.out.println("Enter shop details in format: name address town");
        String[] shopInfo = scanner.nextLine().split("\\s+");
        return shopService.addShop(shopInfo[0], shopInfo[1], shopInfo[2]);
    }

    public String addSeller() {
        System.out.println("Enter seller details in format: firstname lastname age salary shop name");
        String[] sellerInfo = scanner.nextLine().split("\\s+");
        try {
            return sellerService.addSeller(sellerInfo[0], sellerInfo[1], Integer.parseInt(sellerInfo[2]),
                    Double.parseDouble(sellerInfo[3]), sellerInfo[4]);
        } catch (Exception ex) {
            return "Invalid seller, exception caught!";

        }

    }
    public String addProduct() {
        try {
            System.out.println("Enter product details in format: name bestBefore(dd-MM-yyyy) category");
            String[] productInfo = scanner.nextLine().split("\\s+");
            return productService.addProduct(productInfo[0], Double.parseDouble(productInfo[1]), LocalDate.parse(productInfo[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")), productInfo[3]);
        } catch (Exception ex) {
            return "Invalid product, exception caught!";
        }
    }
}