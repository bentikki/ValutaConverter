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

    protected ArrayList<Rate> currencyRates;
    @Override
    public ArrayList<Rate> getCurrentRates() {
        return this.currencyRates;
    }

    public CurrencyMock(){
        ArrayList<Rate> listOfMockRates = new ArrayList<>();

        // Add mock data rates.
        listOfMockRates.add(new Rate("DKK", 1.00));
        listOfMockRates.add(new Rate("EUR", 7.44));
        listOfMockRates.add(new Rate("USD", 6.20));
        listOfMockRates.add(new Rate("CNY", 0.96));

        this.currencyRates = listOfMockRates;
    }

    /**
     * Returns Rate object matching the provided name.
     *
     * If the provided name does not match an object in this.currencyRates, return null
     *
     * @param valutaName selected valuta code must be provided.
     * @return Rate object
     */
    public Rate getRateByName(String valutaName){

        for (Rate valutaRate : this.currencyRates) {
            if (valutaRate.getName().equals(valutaName)) {
                return valutaRate;
            }
        }

        return null;
    }


    /**
     * Returns historical data from provided rate.
     *
     * @param currencyRate selected valuta object must be provided.
     * @return ArrayList<ValutaHistory>
     */
    public ArrayList<ValutaHistory> getHistoricalDataFromRate(Rate currencyRate){

        ArrayList<ValutaHistory> historicalData = new ArrayList<>();

        historicalData.add(new ValutaHistory("01/05/2021", currencyRate));
        historicalData.add(new ValutaHistory("01/04/2021", currencyRate));
        historicalData.add(new ValutaHistory("01/03/2021", currencyRate));
        historicalData.add(new ValutaHistory("01/02/2021", currencyRate));
        historicalData.add(new ValutaHistory("01/01/2021", currencyRate));

        return historicalData;
    }

}
