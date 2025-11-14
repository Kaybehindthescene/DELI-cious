package com.pluralsight.ui;

import com.pluralsight.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    // Create a Scanner object to read user input from the console
    Scanner sc = new Scanner(System.in);

    // The home() method displays the main menu (Home screen)
    // and keeps looping until the user decides to exit.
    public void home(){
        while (true){
            System.out.println("======DELI-cious======");
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
    // Displays the main order menu and handles user choices
    private void orderMenu(Order order) {
        while (true){
            System.out.println("=====Order Menu=====");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.println("Choose: ");

            // Read the user's menu selection
            String choice = sc.nextLine().trim();
            // Process selection using a switch-case block
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

    // a method that guides the user into making a sandwich
    // or lets them pick a premade "signature" sandwich.
    private void addSandwichFlow(Order order){
        // First, ask if they want to build their own or choose a premade sandwich
        System.out.println("Would you like to: ");
        System.out.println("1) Build your own sandwich");
        System.out.println("2) Choose from our signature sandwiches");
        System.out.println("Choose: ");

        String firstChoice = sc.nextLine().trim();
        if (firstChoice.equals("2")){
            addSignatureSandwich(order);// Go to the signature sandwiches menu
            return;
        } else if (!firstChoice.equals("1")) {
            System.out.println("Invalid choice returning to main menu");
         pause(1000);
         return;
        }

        System.out.println("What size sandwich do you want? The options are 4/8/12 inches.");
        Size size;
        try {
            size = Size.parsed(sc.nextLine()); // Convert user input into a Size enum
        }catch (IllegalArgumentException e){
            System.out.println("This is an invalid size!!! Please try again");
            return;
        }

        System.out.println("What type of bread would you like? the options are: WHITE, WHEAT, RYE, SOURDOUGH, WRAP");
        Bread bread;
        try {
            bread = Bread.parsed(sc.nextLine());// Convert user input into a Bread enum
        }catch (IllegalArgumentException e){
            System.out.println("This is an invalid option of bread!!! Please try again");
            return;
        }
        System.out.println("Would you like it toasted (Yes/No)");
        boolean toasted = sc.nextLine().trim().equalsIgnoreCase("Yes"); // Convert Yes/No to true/false

        Sandwich sandwich = new Sandwich(size, bread, toasted);// creates a sandwich object using the user input

        toppingsMenu(sandwich);//enter the topping menu for that sandwich

        System.out.printf("Added: %s $%.2f%n", sandwich.getLabel(), sandwich.getPrice());
        pause(1500);
        order.addSandwich(sandwich,1);//adds completed sandwich to the order
    }
    // Handles the user flow for selecting and adding a drink to the order
    private void addDrink(Order order){
        System.out.println("What drink do you want: SODA, WATER, JUICE: ");
        Drink d;
        try {
            d = Drink.parsed(sc.nextLine());// Convert user input into a Drink enum
        }catch (Exception e){
            System.out.println("This is not a valid option for a drink. Please try again");
            return;
        }
        System.out.println("What size drink would you like: SMALL, MEDIUM, LARGE (S/M/L is also acceptable) ");
        DrinkSize size;
        try {
            size = DrinkSize.parsed(sc.nextLine());// Convert user input into a DrinkSize enum
        }catch (Exception e){
            System.out.println("This is an invalid size. Please try again with a valid size");
            return;
        }
        System.out.println("How many drinks would you like: ");
        int q;
        try {
            q = Integer.parseInt(sc.nextLine().trim());// Convert user input to an integer for quantity
        }catch (Exception e){
            System.out.println("This is an invalid number of drinks. Please try again");
            return;
        }
        order.addDrink(d,size,q);//Add the drink selection(s) to the order
        System.out.printf("Added: %s x%d  $%.2f%n",size.getLabel() + " " + d.getLabel(), q, d.getPrice() * q);
        pause(1500);
    }
    // Handles the process of selecting and adding chips to the order
    private void addChips(Order order){
        System.out.println("What type of chips do you want: Classic, BBQ, Sour Cream ");
        Chips c;
        try {
            c = Chips.parsed(sc.nextLine());// Convert the user's input into a Chips enum value
        }catch (Exception e){
            System.out.println("This is not an available option. Please try again");
            return;
        }
        System.out.println("How many chips would you like: ");
        int q;
        try {
            q = Integer.parseInt(sc.nextLine().trim()); // Convert user input into an integer
        }catch (Exception e){
            System.out.println("This is an invalid number of chips. Please try again");
            return;
        }
        order.addChips(c,q);//Add chips to the order
        System.out.printf("Added: %s x%d  $%.2f%n", c.getLabel(), q, c.getPrice() * q);
        pause(1500);
    }
    private void printCart(Order order){
        if (order.isEmpty()){
            System.out.println("Your cart is empty. Please add some items.");
            return;
        }
        System.out.println("-------------------- Current Order ------------------------------");
        int i = 0;
        for (OrderItem item : order.getItems()){
            System.out.printf("%2d) %-20s x%-2d  @ $%.2f  = $%.2f%n",
                    i++, item.getLabel(), item.getQuantity(), item.getUnitPrice(), item.getOrderTotal());
        }
        System.out.printf("Subtotal: $%.2f%nTax: $%.2f%nTotal: $%.2f%n",
                order.getSubtotal(), order.getTax(), order.getTotal());

    }
    // Handles the checkout process and prints the final receipt
    private void checkOut(Order order) {
        System.out.println("\n Preparing your receipt...");
        pause(1200);
        System.out.println();
        System.out.println("=====================================");
        System.out.println("          FINAL RECEIPT");
        System.out.println("=====================================");
        System.out.println();

        // Call your getReceipt() from Order object
        String receipt = order.getReceipt();
        System.out.println(receipt);

        System.out.println("Please come by again");
        sc.nextLine(); // pause so user can read receipt before returning
    }
    // Displays the toppings menu and allows the user to add toppings to a sandwich
    private void toppingsMenu(Sandwich sandwich){
        while (true){
            System.out.println("\n =====TOPPINGS MENU=====");
            System.out.println("1) Add Meat");
            System.out.println("2) Add Cheese");
            System.out.println("3) Add Extra Meat");
            System.out.println("4) Add Extra Cheese");
            System.out.println("5) Add Regular Toppings");
            System.out.println("6) Add Sauces");
            System.out.println("V) View Current Order");
            System.out.println("D) Done");
            System.out.println("C) Choose: ");

            String c = sc.nextLine().trim().toUpperCase();//stores the user answer in a variable "c" and trims it and
                                                          //converts it to uppercase
            switch (c){
                case "1" -> addFromCategory(sandwich, ToppingType.MEAT);// add normal meat toppping
                case "2" -> addFromCategory(sandwich, ToppingType.CHEESE);//add normal cheese topping
                case "3" ->{ System.out.println("Would you like extra meat? (Yes/No)");
                    String answer = sc.nextLine().trim().toLowerCase();//stores answer in variable
                    if (answer.equalsIgnoreCase("yes")){
                        System.out.println("Extra meat added!!!");
                        pause(800);
                        sandwich.addTopping(Topping.EXTRA_MEAT);//add extra meat topping
                    }else {
                        System.out.println("Extra meat was not added");
                        pause(800);
                    }
                    return;
                }
                case "4" -> {System.out.println("Would you like extra cheese? (Yes/No)");
                    String answer = sc.nextLine().trim().toLowerCase();//stores answer in variable
                    if (answer.equalsIgnoreCase("yes")){
                        System.out.println("Extra cheese added!!!");
                        pause(800);
                        sandwich.addTopping(Topping.EXTRA_CHEESE);//add extra cheese topping
                    }else {
                        System.out.println("Extra cheese was not added");
                        pause(800);
                    }
                    return;

                }
                case "5" -> addFromCategory(sandwich, ToppingType.REGULAR);//add regular topping vegetables
                case "6" -> addFromCategory(sandwich, ToppingType.SAUCE);//add sauce toppings
                case "V" -> {
                    printCurrentSandwich(sandwich);// Show the current sandwich summary
                    pause(1500);
                }
                case "D" -> { return; }// Done adding toppings, return to the sandwich builder
                default -> System.out.println("Invalid option.");
            }
        }
    }
    // Adds toppings from a specific category (Meat, Cheese, Regular, Sauce)
    // This method handles the typed comma-separated inputs for normal topping groups.
    private void addFromCategory(Sandwich sandwich, ToppingType type){
        List<Topping> options = optionsFor(type);// Get all toppings that belong to the selected category
        if (options.isEmpty()){
            System.out.println("No options available.");
            return; // If the category has no available toppings, exit early
        }
        System.out.println("\n=====" + type.name() + " OPTIONS=====");
        for (Topping t : options){
            double price = t.getPrice(sandwich.getSize());//Calculate topping cost based on sandwich size
            System.out.printf("- %s  %s%n",
                    t.name(),
                    price == 0.0 ? "(included)" : String.format("$%.2f", price));
        }

        System.out.println("Type names separated by commas (or Enter to skip): ");//Ask user for toppings using comma-separated format
        String input = sc.nextLine().trim();//stores the user answer in a variable
        if (input.isEmpty()){
            return;//If the user presses Enter, they are skipping this category
        }
        int added = 0;// Count how many toppings were added
        // Split input by commas and process each topping individually
        for (String raw : input.split(",")){
            String token = raw.trim();
            try {
                Topping top = Topping.parse(token);// Convert typed string into a Topping enum
                if (top.getType() != type){
                    System.out.println("Skipping (wrong category): " + token);
                    continue;
                }
                //add topping to sandwich
                sandwich.addTopping(top);
                added++;
            }catch (Exception e){
                System.out.println("Skipping invalid topping: " + token);

            }
        }
        // If toppings were added, show confirmation message and updated price
        if (added > 0){
            System.out.printf("%d topping(s) added. Price now: $%.2f%n",
                    added, sandwich.getPrice());
        }
        pause(2000);

    }
    // Returns a list of all toppings that belong to the given ToppingType.
    private List<Topping> optionsFor(ToppingType type){
        List<Topping> list = new ArrayList<Topping>();// Create a new list that will hold all matching toppings
        // Loop through every topping
        // If this topping belongs to the category we want
        // Add it to the list of options we will return
        for (Topping t : Topping.values() ) if (t.getType() == type) list.add(t);
        return list;
    }
    // Displays the user's current sandwich details
    private void printCurrentSandwich(Sandwich s) {
        System.out.println("\n-- Current Sandwich --");
        String line = s.getSize().getLabel() + " " + s.getBread().getDisplayName();// Build the main line showing size + bread type
        if (s.isToasted()) {
            line += "(toasted)";
        }
        System.out.println(line); // Print the main sandwich description
        // Loop through each topping and print it in a list format
        for (Topping t : s.getToppings())
            System.out.println("  + " + toTitleCase(t.name()));// Convert ENUM_NAME to a readable format

        System.out.printf("Price so far: $%.2f%n", s.getPrice()); // Show the running total of the sandwich so far
    }
    // method made to pauses the program for the given amount of time
    // Used to slow down transitions so the user can read messages before the next menu appears
    private void pause(long time){
        try {
            Thread.sleep(time);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

    }
    // Converts an enum-style string into title case
    private String toTitleCase(String enumName) {
        //convert the entire string to lowercase and replace underscores with space
        String[] parts = enumName.toLowerCase().replace('_', ' ').split("\\s+");
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;//skip empty strings
            //Capitalize the first letter and append the rest of the word
            b.append(Character.toUpperCase(parts[i].charAt(0)))
                    .append(parts[i].substring(1));
            if (i < parts.length - 1) b.append(' ');//Add a space between words, but not after the last one
        }
        return b.toString();//Add a space between words, but not after the last one
    }
    // Handles the process of adding a pre-made (signature) sandwich to the order
    private void addSignatureSandwich(Order order){
        System.out.println("\n=====SIGNATURE SANDWICHES=====");
        System.out.println("1) BLT");
        System.out.println("2) Philly CheeseSteak");
        System.out.println("3) Chopped Cheese");
        System.out.println("0) Back");
        System.out.println("Choose: ");

        String choice = sc.nextLine().trim();
        // We declare the sandwich variable here so it can be assigned inside the switch
        Sandwich sandwich = null;

        switch (choice){
            case "1" -> sandwich = new BltSandwich();
            case "2" -> sandwich = new PhillyCheeseSteak();
            case "3" -> sandwich = new ChoppedCheese();
            case "0"-> {
                System.out.println("Returning to sandwich options");
                pause(900);
                return;
            }
            default -> {
                System.out.println("Invalid signature choice. Returning to sandwich options.");
                pause(800);
                return;
            }
        }
        System.out.println("\nYou chose:");
        printCurrentSandwich(sandwich);

        System.out.println("\nWould you like to add extra toppings on the sandwich? (Yes/No): ");
        String answer = sc.nextLine().trim().toLowerCase();
        if (answer.equalsIgnoreCase("yes")){
            toppingsMenu(sandwich);
        }
        order.addSandwich(sandwich, 1);
        System.out.printf("Added to order: %s  $%.2f%n", sandwich.getLabel(), sandwich.getPrice());
        pause(1500);
    }






}
