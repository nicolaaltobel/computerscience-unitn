package com.company.data;

public class ECar extends Car {
    protected double bat;

    // constructor
    public ECar(int id, String maker, int year, String model, double cost, double weight, String drivetype, String fueltype, double bat) {
        super(id, maker, year, model, cost, weight, drivetype, fueltype);
        this.bat = bat;
    }

    // getters
    public double getBat() { return bat; }

    // setters
    public void setBat(double bat) { this.bat = bat; }

    // override toString
    @Override
    public String toString() {
        return super.toString() + " Bat: " + bat;
    }

}
