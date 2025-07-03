package com.company.data;

public class Motorbike extends Vehicle {


    // constructor
    public Motorbike(int id, String maker, int year, String model, double cost, double weight) {
        super(id, maker, year, model, cost, weight);
        this.type = "B";
    }

    // override toString
    @Override
    public String toString() {
        return super.toString();
    }
}
