package com.company.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Formatter;

public class Sale implements Comparable<Sale> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static int count = 1;
    private int id;
    private LocalDateTime date;
    private int vehicleID;
    private int amount;
    private double price;
    private double totalPrice;
    private String maker;
    private String model;

    // constructor
    public Sale(int vehicleID, int amount, String maker, String model, double price) {
        this.id = count++;
        this.date = LocalDateTime.now();
        this.vehicleID = vehicleID;
        this.amount = amount;
        this.maker = maker;
        this.totalPrice = price * amount;
        this.model = model;
        this.price = price;

    }

    // getters
    public int getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public int getVehicle() { return vehicleID; }
    public int getAmount() { return amount; }
    public String getMaker() { return maker; }
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public double getTotalPrice() { return totalPrice; }

    // setters
    public void setId(int id) { this.id = id; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setVehicle(int vehicleID) { this.vehicleID = vehicleID; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setMaker(String maker) { this.maker = maker; }
    public void setModel(String model) { this.model = model; }
    public void setPrice(double price) { this.price = price; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }


    // override toString method
    @Override
    public String toString() {
        return "ID: " + id + " Date: " + date.format(formatter) + " VID: " + vehicleID + "\t Cost: " + price + " #: " + amount + " Total: " + totalPrice + " Model: " + model;
    }


    // implement compareTo, which is the first comparator and will compare by total cost descending
    public int compareTo(Sale o) {
        if (this.getTotalPrice() == o.getTotalPrice()) {
            return 0;
        }
        return (o.getTotalPrice() < this.getTotalPrice()) ? -1 : 1;
    }

    // second comparator to order by date descending
    public static Comparator<Sale> dateComparator = new Comparator<Sale>() {
        public int compare(Sale o1, Sale o2) {
            return o2.getDate().compareTo(o1.getDate());
        }
    };

    // third comparator to order by maker (ascending) and cost (descending)
    public static Comparator<Sale> makerComparator = new Comparator<Sale>() {
        public int compare(Sale o1, Sale o2) {
            if (o1.maker.compareTo(o2.maker) == 0) { // if maker is equal, then compare by cost
                if (o1.getTotalPrice() == o2.getTotalPrice()) {
                    return 0;
                }
                return (o2.getTotalPrice() < o1.getTotalPrice()) ? -1 : 1;
            } else {
                return o1.maker.compareTo(o2.maker); // else just compare by maker
            }
        }
    };
}
