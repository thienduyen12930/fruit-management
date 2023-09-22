/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Library;
import java.util.ArrayList;
import java.util.Hashtable;
import model.Fruit;
import model.Order;
import view.Menu;

public class ManageFruit extends Menu {

    static String[] mc = {"Create Fruit", "View Orders ", "Shopping    ", "Exit        "};
    Library l;
    ArrayList<Fruit> list_F;
    Hashtable<String, ArrayList<Order>> ht;

    public ManageFruit() {
        super("==== FRUIT SHOP SYSTEM ====", mc);
        l = new Library();
        list_F = new ArrayList<>();
        ht = new Hashtable<>();
    }

    public void execute(int n) {
        switch (n) {
            case 1:
                createFruit();
                break;
            case 2:
                viewListOrder();
                break;
            case 3:
                shopping();
                break;
            case 4:
                System.exit(0);
        }
    }

    public void viewListOrder() {
        if (ht.isEmpty()) {
            System.out.println("No Order");
            return;
        }
        for (String name : ht.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> array_o = ht.get(name);
            displayListOrder(array_o);
        }
    }

    public void createFruit() {
        int id = generateID();
        String name = l.getString("Enter fruit name: ");
        double price = l.getDouble("Enter fruit price");
        int quantity = l.getInt("Enter fruit quantity", 1, 100);
        String origin = l.getString("Enter fruit origin: ");
        list_F.add(new Fruit(id, name, price, quantity, origin));
    }

    public void displayFruit() {
        System.out.printf("| %-4s | %-15s | %-10s | %-10s | %-20s |\n", "ID", "Name", "Price", "Quantity", "Origin");

        for (Fruit f : list_F) {
            System.out.printf("| %-4d | %-15s | %-10.2f | %-10d | %-20s |\n", f.getId(), f.getName(), f.getPrice(), f.getQuantity(), f.getOrigin());
        }

        System.out.println("-----------------------------------------------------------------------------");

    }

    public void shopping() {
        displayFruit();
        String name = l.getString("Enter fruit name: ");
        int quantityOrder = l.getInt("Please input quantity", 1, 10);
        ArrayList<Order> list_o = new ArrayList<>();
        boolean found = false;

        for (Fruit f : list_F) {
            if (name.equals(f.getName())) {
                int id = f.getId();
                double price = f.getPrice();
                int quantity = f.getQuantity();

                if (quantity == 0) {
                    System.out.println("Sorry, the fruit is out of stock.");
                } else if (quantityOrder > quantity) {
                    System.out.println("Quantity Order more than quantity");
                } else {
                    list_o.add(new Order(id, name, quantityOrder, price));
                    displayListOrder(list_o);
                    String customer = l.getString("Enter Customer of name: ");
                    ht.putIfAbsent(customer, new ArrayList<>());
                    ht.get(customer).addAll(list_o);
                    System.out.println("Add Successfull");
                    f.setQuantity(quantity - quantityOrder);
                    found = true;
                }
            }
        }
    }

    private void displayListOrder(ArrayList<Order> list_o) {
        double total = 0;
        System.out.printf("| %-4s | %-15s | %-10s | %-10s |\n", "ID", "name", "quanlity", "price");
        for (Order o : list_o) {
            System.out.printf("| %-4d | %-15s | %-10d | %-10.2f |\n", o.getId(), o.getName(), o.getQuanlity(), o.getPrice());
            total += o.getPrice() * o.getQuanlity();
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Total: " + total);
    }

    public int generateID() {
        int id = 0;
        if (list_F.size() == 0) {
            return 1;
        } else {

            for (Fruit s : list_F) {
                if (s.getId() == list_F.size()) {
                    id = s.getId() + 1;
                }
            }
        }
        return id;
    }
}
