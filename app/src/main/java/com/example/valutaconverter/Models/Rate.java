package com.example.valutaconverter.Models;

/**
 * POGO Class that holds the currency rate data.
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public class Rate {

    String name;
    double currentRate;

    public String getName(){ return this.name; }
    public double getCurrentRate(){ return this.currentRate; }


    public Rate (String name, double currentRate){
        this.name = name;
        this.currentRate = currentRate;
    }

}
