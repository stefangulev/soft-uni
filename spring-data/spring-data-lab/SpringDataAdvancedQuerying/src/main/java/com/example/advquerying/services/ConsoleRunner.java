package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
@Service
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;


    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }


    @Override
    public void run(String... args) throws Exception {
       Scanner scan = new Scanner(System.in);
//        System.out.println("Task 1");
//        System.out.println("Enter shampoo size:");
//        Size size = Size.valueOf(scan.nextLine());
//        shampooService.findAllShampoosBySizeOrderedById(size).forEach(System.out::println);
//        System.out.println("Task 2");
//        System.out.println("Enter shampoo size:");
//        Size size = Size.valueOf(scan.nextLine());
//        System.out.println("Enter label ID");
//        long labelId = Long.parseLong(scan.nextLine());
//        shampooService.findAllShampoosBySizeOrLabel(size, labelId).forEach(System.out::println);
//        System.out.println("Task 3");
//        System.out.println("Enter min price");
//        BigDecimal price = new BigDecimal(scan.nextLine());
//        shampooService.findAllShampoosWithPriceHigherThan(price).forEach(System.out::println);
//        System.out.println("Task 4");
//        System.out.println("Enter letter seq.");
//        String pattern = scan.nextLine();
//       ingredientService.findAllIngredientsStartingWith(pattern).forEach(System.out::println);
//        System.out.println("Task 5");
//        System.out.println("Input the list");
//        List<String> strings = Arrays.asList(scan.nextLine().split("\\s+"));
//        ingredientService.findAllIngredientsFromListAndOrderByPrinceAsc(strings).forEach(System.out::println);
//        System.out.println("Task 6");
//        System.out.println("Input max price");
//        BigDecimal price = new BigDecimal(scan.nextLine());
//        System.out.println(shampooService.findCountOfShampoosWithPriceLowerThan(price));
//        System.out.println("Task 7");
//        System.out.println("Input list");
//        List<String> ingredients = Arrays.asList(scan.nextLine().split(" "));
//        shampooService.findShampoosByIngredientList(ingredients).forEach(System.out::println);
//        System.out.println("Task 8");
//        System.out.println("Enther ingredient count");
//        int count = Integer.parseInt(scan.nextLine());
//        shampooService.findShampoosByIngredientCount(count).forEach(System.out::println);
//        System.out.println("Task 9");
//        System.out.println("Enter name:");
//        String name = scan.nextLine();
//        System.out.println(ingredientService.deleteByName("Apple"));
//        System.out.println("Task 10");
//        System.out.println(ingredientService.updateBy10Percent());
        System.out.println("Task 11");
        System.out.println("Enter ingredient names");
        List<String> strings = Arrays.asList(scan.nextLine().split("\\s+"));
        System.out.println("Enter percentage to raise price");
        double percentage = Double.parseDouble(scan.nextLine());
        System.out.println(ingredientService.updateByGivenPercentageWhereIngredientInList(percentage, strings));

    }


}
