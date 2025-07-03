package com.company.data;

public class Car extends Vehicle{
    protected String drivetype;
    protected String fueltype;

    // constructor
    public Car(int id, String maker, int year, String model, double cost, double weight, String drivetype, String fueltype) {
        super(id, maker, year, model, cost, weight);
        this.drivetype = drivetype;
        this.fueltype = fueltype;
    }

    // getters
    public String getDrivetype() { return drivetype; }
    public String getFueltype() { return fueltype; }

    // setters
    public void setDrivetype(String drivetype) { this.drivetype = drivetype; }
    public void setFueltype(String fueltype) { this.fueltype = fueltype; }


    // override toString
    @Override
    public String toString() {
        return super.toString() + " Engine: " + fueltype + " Drive: " + drivetype;
    }
}
