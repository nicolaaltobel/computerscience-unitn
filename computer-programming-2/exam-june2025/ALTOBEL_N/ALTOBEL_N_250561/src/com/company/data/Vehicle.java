package com.company.data;

import java.util.Comparator;

public class Vehicle implements Comparable<Vehicle> {
    protected int id;
    protected String maker;
    protected String type;
    protected int year;
    protected String model;
    protected double cost;
    protected double weight;

    // constructor
    public Vehicle(int id, String maker, int year, String model, double cost, double weight) {
        this.id = id;
        this.maker = maker;
        this.year = year;
        this.model = model;
        this.cost = cost;
        this.weight = weight;
        this.type = "C";
    }

    // getters
    public int getId() { return id; }
    public String getMaker() { return maker; }
    public int getYear() { return year; }
    public String getModel() { return model; }
    public double getCost() { return cost; }
    public double getWeight() { return weight; }
    public String getType() { return type; }

    // setters
    public void setId(int id) { this.id = id; }
    public void setMaker(String maker) { this.maker = maker; }
    public void setYear(int year) { this.year = year; }
    public void setModel(String model) { this.model = model; }
    public void setCost(double cost) { this.cost = cost; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setType(String type) { this.type = type; }


    // overriding toString method
    @Override
    public String toString() {
        return "Id: " + id + " " + type + " Y: " + year + "\t" + maker + " Name: " + model + "\t\t\t\t\t Price: " + cost + " Weight: " + weight;
    }

    // implement the compareTo method, which is the first comparator and orders by year descending and com.company.model ascending
    public int compareTo(Vehicle o) {
        if (o.year == year){ // if years are equal, compare by com.company.model
            return this.model.compareTo(o.model);
        }
        return o.year - year; // else just compare the years
    }

    // second comparator to order by maker ascending and cost descending
    public static Comparator<Vehicle> makerComparator = new Comparator<Vehicle>() {
        public int compare(Vehicle o1, Vehicle o2) {
            if (o1.getMaker().equals(o2.getMaker())){ // if maker is equal, compare by cost
                return (o1.getCost() < o2.getCost()) ? 1 : -1;
            }

            return o1.getMaker().compareTo(o2.getMaker()); // else just compare by maker
    }
    };

    // third comparator to order by weight descending
    public static Comparator<Vehicle> weightComparator = new Comparator<Vehicle>() {
        public int compare(Vehicle o1, Vehicle o2) {
            if (o1.getWeight() == o2.getWeight()){
                return 0;
            }
            return (o1.getWeight() < o2.getWeight()) ? 1 : -1;
        }
    };
}
