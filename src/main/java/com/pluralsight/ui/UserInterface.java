package com.pluralsight.ui;

import com.pluralsight.models.Order;

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
                    System.out.println("Add Sandwich ");
                    addSandwich();
                    break;
                case "2":
                    System.out.println("Add Drink ");
                    addDrink();
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


    private void addSandwich(){
        System.out.println("Added sandwich");
    }
    private void addDrink(){
        System.out.println("added drink");
    }
    private void addChips(){
        System.out.println("added chips");
    }
    private void checkOut(){
        System.out.println("checkout");
    }




}
