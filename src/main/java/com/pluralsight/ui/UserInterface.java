package com.pluralsight.ui;

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
                orderMenuPlaceholder();
            }else {
                if (choice.equals("0")){
                    return;
                }
            }
        }

    }
    private void orderMenuPlaceholder() {
        System.out.println("(Order screen placeholder)");
    }


}
