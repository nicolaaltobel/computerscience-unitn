package com.company.model;

import com.company.data.Sale;

import java.util.ArrayList;

public class SalesList extends ArrayList<Sale> {

    // constructor
    public SalesList() {
        super();
    }

    // override toString method
    @Override
    public String toString() {
        String outString = "";

        for (Sale s : this) {
            outString = outString.concat(s.toString() + "\n");
        }
        return outString;
    }
}
