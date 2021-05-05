package com.example.valutaconverter.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * CurrencyDAO that returns Mock currency data.
 *
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public class CurrencyMock implements CurrencyDAO{

    private ArrayList<Rate> currencyRates;

    public CurrencyMock(){
        ArrayList<Rate> listOfMockRates = new ArrayList<>();

        // Add mock data rates.
        listOfMockRates.add(new Rate("DKK", 1.00));
        listOfMockRates.add(new Rate("EUR", 7.44));
        listOfMockRates.add(new Rate("USD", 6.20));
        listOfMockRates.add(new Rate("CNY", 0.96));

        this.currencyRates = listOfMockRates;
    }

    @Override
    public ArrayList<Rate> getCurrentRates() {
        return this.currencyRates;
    }
}
