package com.pluralsight.ui;

import com.pluralsight.models.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    // Create a Scanner object to read user input from the console
    Scanner sc = new Scanner(System.in);

    // The home() method displays the main menu (Home screen)
    // and keeps looping until the user decides to exit.
    public void home(){
        while (true){
            System.out.println("==== DELI-cious ====");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine();
            if (choice.equals("1")){
                Order order = new Order();
               orderMenu(order);
            }else {
                if (choice.equals("0")){
                    return;
                }
            }
        }

    }

    private void orderMenu(Order order) {
        while (true){
            System.out.println("=====Order Menu=====");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.println("Choose: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addSandwichFlow(order);
                    break;
                case "2":
                    System.out.println("Add Drink ");
                    addDrink(order);
                    break;
                case "3":
                    System.out.println("Add Chips ");
                    addChips(order);
                    break;
                case "4":
                    System.out.println("Checkout ");
                    checkOut(order);
                    break;
                case "0":
                    return;

                default:
                    System.out.println("Invalid Option!!!");
                    break;
            }
        }
    }
    private void addSandwichFlow(Order order){
        System.out.println("What size sandwich do you want? The options are 4/8/12 inches.");
        Size size;
        try {
            size = Size.parsed(sc.nextLine());
        }catch (IllegalArgumentException e){
            System.out.println("This is an invalid size!!! Please try again");
            return;
        }

        System.out.println("What type of bread would you like? the options are: WHITE, WHEAT, RYE, SOURDOUGH, WRAP");
        Bread bread;
        try {
            bread = Bread.parsed(sc.nextLine());
        }catch (IllegalArgumentException e){
            System.out.println("This is an invalid option of bread!!! Please try again");
            return;
        }
        System.out.println("Would you like it toasted (Yes/No)");
        boolean toasted = sc.nextLine().trim().equalsIgnoreCase("Yes");

        Sandwich sandwich = new Sandwich(size, bread, toasted);

        toppingsMenu(sandwich);

        System.out.printf("Added: %s $%.2f%n", sandwich.getLabel(), sandwich.getPrice());
        order.addSandwich(sandwich,1);
    }

    private void addDrink(Order order){
        System.out.println("What drink do you want: SODA, WATER, JUICE: ");
        Drink d;
        try {
            d = Drink.parsed(sc.nextLine());
        }catch (Exception e){
            System.out.println("This is not a valid option for a drink. Please try again");
            return;
        }
        System.out.println("How many drinks would you like: ");
        int q;
        try {
            q = Integer.parseInt(sc.nextLine().trim());
        }catch (Exception e){
            System.out.println("This is an invalid number of drinks. Please try again");
            return;
        }
        order.addDrink(d,q);
        System.out.printf("Added: %s x%d  $%.2f%n", d.getLabel(), q, d.getPrice() * q);
    }
    private void addChips(Order order){
        System.out.println("What type of chips do you want: Classic, BBQ, Sour Cream ");
        Chips c;
        try {
            c = Chips.parsed(sc.nextLine());
        }catch (Exception e){
            System.out.println("This is not an available option. Please try again");
            return;
        }
        System.out.println("How many chips would you like: ");
        int q;
        try {
            q = Integer.parseInt(sc.nextLine().trim());
        }catch (Exception e){
            System.out.println("This is an invalid number of chips. Please try again");
            return;
        }
        order.addChips(c,q);
        System.out.printf("Added: %s x%d  $%.2f%n", c.getLabel(), q, c.getPrice() * q);
    }
    private void printCart(Order order){
        if (order.isEmpty()){
            System.out.println("Your cart is empty. Please add some items.");
            return;
        }
        System.out.println("-------- Current Order ----------");
        int i = 0;
        for (OrderItem item : order.getItems()){
            System.out.printf("%2d) %-20s x%-2d  @ $%.2f  = $%.2f%n",
                    i++, item.getLabel(), item.getQuantity(), item.getUnitPrice(), item.getOrderTotal());
        }
        System.out.printf("Subtotal: $%.2f%nTax: $%.2f%nTotal: $%.2f%n",
                order.getSubtotal(), order.getTax(), order.getTotal());

    }
    private void checkOut(Order order){
        if (order.isEmpty()){
            System.out.println("No items to checkout");
            return;
        }
        System.out.println("====== RECEIPT =====");
        printCart(order);
        System.out.println("====================");

        order.clear();
        System.out.println("Thank you for placing an order with us. Hope to see you again");
    }
    private void toppingsMenu(Sandwich sandwich){
        while (true){
            System.out.println("\n ===== TOPPINGS MENU =====");
            System.out.println("1) Add Meat");
            System.out.println("2) Add Cheese");
            System.out.println("3) Add Extra Meat");
            System.out.println("4) Add Extra Cheese");
            System.out.println("5) Add Regular Toppings");
            System.out.println("6) Add Sauces");
            System.out.println("V) View Current Order");
            System.out.println("D) Done");
            System.out.println("C) Choose: ");

            String c = sc.nextLine().trim().toUpperCase();
            switch (c){
                case "1" -> addFromCategory(sandwich, ToppingType.MEAT);
                case "2" -> addFromCategory(sandwich, ToppingType.CHEESE);
                case "3" -> addFromCategory(sandwich, ToppingType.EXTRA_MEAT);
                case "4" -> addFromCategory(sandwich, ToppingType.EXTRA_CHEESE);
                case "5" -> addFromCategory(sandwich, ToppingType.REGULAR);
                case "6" -> addFromCategory(sandwich, ToppingType.SAUCE);
                case "V" -> printCurrentSandwich(sandwich);
                case "D" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    private void addFromCategory(Sandwich sandwich, ToppingType type){
        List<Topping> options = optionsFor(type);
        if (options.isEmpty()){
            System.out.println("No options available.");
            return;
        }
        System.out.println("\n--" + type + "options --");
        for (Topping t : options){
            double price = t.getPrice(sandwich.getSize());
            System.out.printf("- %s  %s%n",
                    t.name(),
                    price == 0.0 ? "(included)" : String.format("$%.2f", price));
        }
        System.out.println("Type names separated by commas (or Enter to skip): ");
        String input = sc.nextLine().trim();
        if (input.isEmpty()){
            return;
        }
        int added = 0;
        for (String raw : input.split(",")){
            String token = raw.trim();
            try {
                Topping top = Topping.parse(token);
                if (top.getType() != type){
                    System.out.println("Skipping (wrong category): " + token);
                    continue;
                }
                sandwich.addTopping(top);
                added++;
            }catch (Exception e){
                System.out.println("Skipping invalid topping: " + token);

            }
        }
        if (added > 0){
            System.out.printf("%d topping(s) added. Price now: $%.2f%n",
                    added, sandwich.getPrice());
        }

    }




}
