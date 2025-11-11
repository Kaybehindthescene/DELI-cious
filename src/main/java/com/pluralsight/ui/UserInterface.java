package com.pluralsight.ui;

import com.pluralsight.models.*;

import java.io.IOException;
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
                    addChips();
                    break;
                case "4":
                    System.out.println("Checkout ");
                    checkOut();
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
        order.addSandwich(sandwich,1);
        System.out.printf("Added: %s $%.2f%n", sandwich.getLabel(), sandwich.getPrice());
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
    private void addChips(){
        System.out.println("added chips");
    }
    private void checkOut(){
        System.out.println("checkout");
    }




}
