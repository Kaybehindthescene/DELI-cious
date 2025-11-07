package com.pluralsight.models;

import java.util.Scanner;

public class Order {

    Scanner sc = new Scanner(System.in);

    private void orderMenu(Order order) {
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
                System.out.println("Add Sandwich " + addSandwich);
                break;
            case "2":
                System.out.println("Add Drink " + addDrink);
                break;
            case "3":
                System.out.println("Add Chips " + addChips);
                break;
            case "4":
                System.out.println("Checkout " + checkOut);
                break;
            case "0":
                return;

            default:
                System.out.println("Invalid Option!!!");


        }
    }

    private v

}

