package com.example.valutaconverter.Models;

public class ValutaHistory {

    private String date;
    private Rate valutaRate;

    public ValutaHistory(String date, Rate valutaRate) {
        this.date = date;
        this.valutaRate = valutaRate;
    }

    public String getDate() {
        return date;
    }

    public Rate getValutaRate() {
        return valutaRate;
    }
}
