#  DELI-cious Sandwich Ordering System  

*A Java CLI app for building custom sandwiches, signature subs, drinks, chips, and printing a detailed deli-style receipt.*

---

## 📑 Table of Contents  

- [Overview](#overview)  
- [Features](#features)  
- [How It Works](#how-it-works)  
- [Screens & Menus](#screens--menus)  
- [Signature Sandwiches](#signature-sandwiches)  
- [Receipt Example](#receipt-example)  
- [My Favorite Part To Work On](#my-favorite-part-to-work-on)  
- [Author](#author)  
- [Technologies Used](#technologies-used)  
- [Purpose](#purpose)  

---

## Overview  

The **DELI-cious Sandwich Ordering System** is a Java command-line application where customers can build fully customizable sandwiches, choose signature creations, add drinks or chips, and receive a formatted receipt including tax and totals.

This program demonstrates **Object-Oriented Programming**, **custom enums**, **inheritance**, **polymorphism**, and **formatted console output**, wrapped in a complete real-world ordering workflow.

It behaves like a real deli ordering computer — but in the terminal.

---

## Features  

### Custom Sandwich Builder  

Users can choose:

- Size (4", 8", 12")  
- Bread type (White, Wheat, Rye, Sourdough, Wrap)  
- Toasted or not  
- Meat, cheese, regular toppings, and sauces  
- Extra meat & extra cheese (Yes/No prompt)  

### Signature Sandwiches  

Custom sandwich classes using inheritance (e.g., **BLT**, **Philly Cheese Steak**, **Chopped Cheese**).  

Each one:  

- Extends the base `Sandwich` class  
- Pre-loads default bread, size, toasted setting, and toppings  
- Can still be customized with extra toppings afterward  

### Drinks & Chips  

- Multiple drink types (**Soda, Water, Juice**)  
- Drink sizes (**Small, Medium, Large**) using a `DrinkSize` enum  
- Several chip flavors (**Classic, BBQ, Sour Cream**)  

Add any combination with automatic price calculation.

### Detailed Receipt  

Printed at checkout with:

- Items ordered  
- Toppings listed under each sandwich  
- Prices aligned in columns  
- Subtotal, tax, and final total  
- Auto-generated order ID & date  

### Built-in Pauses For Realistic Flow  

The program uses small delays (via a `pause(long time)` helper) to give users time to read messages before moving on.  
This makes the console feel more like a real ordering system instead of instant walls of text.

### Input Validation  

Handles invalid sizes, toppings, categories, drink sizes, and more:
- Invalid size → error + return to menu  
- Invalid bread → error + return  
- Invalid topping name or wrong category → skipped with a friendly message  

---

## How It Works  

1. **User Starts an Order**  
   - Selecting **“Add Sandwich”** walks them through size → bread → toasted → toppings.

2. **Signature Sandwiches Available**  
   - Users may pick a pre-built sandwich (BLT, Philly Cheese Steak, Chopped Cheese) and then optionally customize it with extra toppings.

3. **Items Become `OrderItem` Objects**  
   - Each sandwich, drink, or chips item is represented by a class implementing `OrderItem`, which provides a consistent way to calculate totals.

4. **Order Subtotal & Tax**  
   - The `Order` class holds a list of `OrderItem`s.  
   - `getSubtotal()`, `getTax()`, and `getTotal()` compute the final numbers using a `taxRate` constant.

5. **Receipt Generation**  
   - `Order.getReceipt()` builds a formatted receipt using `StringBuilder` and `String.format`, listing every sandwich, its toppings, and each regular item.

6. **Checkout**  
   - When the user chooses **Checkout**, the app prints the final receipt, shows the totals, and thanks the customer.

---

## Screens & Menus  

### Home / Order Menu  


```text
=====Order Menu=====
1) Add Sandwich
2) Add Drink
3) Add Chips
4) Checkout
0) Cancel Order
Choose:


=====TOPPINGS MENU=====
1) Add Meat
2) Add Cheese
3) Add Extra Meat
4) Add Extra Cheese
5) Add Regular Toppings
6) Add Sauces
V) View Current Order
D) Done
C) Choose:


-- Current Sandwich --
8" Wheat (toasted)
  + Ham
  + American
  + Lettuce
  + Jalapenos
  + Mayo
Price so far: $7.10
```
## Signature Sandwiches
The Signature Sandwiches feature adds several pre-built sandwich options for users who want a quick and ready-made choice.
 Each signature sandwich (BLT, Philly Cheese Steak, Chopped Cheese) is implemented as its own class that extends the `Sandwich` class,
automatically loading its default toppings and structure. This allows customers to select a preset creation instantly while still having the flexibility to customize it afterward.

##  Receipt Example

Here is an example of the formatted receipt:

```
*************************************
        DELI-cious Sandwich Co.
      123 Java Street, IDE City
          (555) 123-DELI
-------------------------------------
Order ID: #1825     Date: 2025-11-12
-------------------------------------
1x 8" Rye (toasted)
   + Swiss
   + Bacon
   + Lettuce
   + Pickles
   + Jalapenos
   + Mayo
   + Mustard
                           $10.85

1x Chips - BBQ              $1.59
1x Medium Juice             $2.50
-------------------------------------
Subtotal                    $14.94
Tax (8.875%)                $1.33
Total                       $16.27
*************************************
      Thanks for stopping by!
*************************************
```

##  My Favorite Part To Work On

My favorite part of this project was structuring the receipt. Making the receipt look and feel like a real deli printout required careful formatting — aligning prices, spacing items correctly, listing toppings underneath each sandwich, and balancing everything so it looked clean in a plain terminal window. 

Designing the receipt format helped me practice attention to detail and improve how I think about user-facing output.

## Author  
**Created by:** *Caleb Shufford*  
**Program:** Year Up United — Software Development Track  
**Date:** November 2025  


## Technologies Used  
- **Java 17+**  
- Object-Oriented Programming (OOP)  
- Inheritance & Polymorphism  
- Custom Enums & Classes  
- Collections & Lists  
- File I/O (java.io & java.nio)  
- Console-based User Interface  
- Formatting with `String.format()`  
- User input handling with `Scanner`  


## Purpose  
This project was created to demonstrate:  
- Building a real-world ordering workflow in Java  
- Applying inheritance to create signature sandwich templates  
- Structuring a multi-step menu system  
- Using enums and classes to represent sandwiches, toppings, sizes, and items  
- Formatting a complete deli-style receipt with totals and tax  
- Designing a realistic user experience entirely in the terminal  

The DELI-cious Sandwich Ordering System is a showcase of clean code structure, problem-solving, and practical application of core Java concepts.

