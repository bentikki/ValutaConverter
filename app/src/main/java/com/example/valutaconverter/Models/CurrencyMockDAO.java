package com.example.valutaconverter.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CurrencyDAO that returns Mock currency data.
 *
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public class CurrencyMockDAO implements CurrencyDAO{

    private final String ORIGIN_CURRENCY = "DKK";
    private ArrayList<Rate> currencyRates;
    private ArrayList<ValutaHistory> historicalRates;

    @Override
    public ArrayList<Rate> getCurrentRates() {
        return this.currencyRates;
    }

    public CurrencyMockDAO() {
        ArrayList<Rate> listOfMockRates = new ArrayList<>();

        // Add mock data rates.
        listOfMockRates.add(new Rate("DKK", 1.00));
        listOfMockRates.add(new Rate("EUR", 7.44));
        listOfMockRates.add(new Rate("USD", 6.20));
        listOfMockRates.add(new Rate("CNY", 0.96));

        this.currencyRates = listOfMockRates;

        ArrayList<ValutaHistory> listOfMockValutaHistories = new ArrayList<>();

        // Add mock data rates.
        listOfMockValutaHistories.add(new ValutaHistory("01/02/2021", new Rate("EUR", 7.30)));
        listOfMockValutaHistories.add(new ValutaHistory("01/03/2021", new Rate("EUR", 7.50)));
        listOfMockValutaHistories.add(new ValutaHistory("01/04/2021", new Rate("EUR", 7.42)));
        listOfMockValutaHistories.add(new ValutaHistory("01/05/2021", new Rate("EUR", 7.45)));

        // Add mock data rates.
        listOfMockValutaHistories.add(new ValutaHistory("01/02/2021", new Rate("USD", 5.92)));
        listOfMockValutaHistories.add(new ValutaHistory("01/03/2021", new Rate("USD", 6.09)));
        listOfMockValutaHistories.add(new ValutaHistory("01/04/2021", new Rate("USD", 6.10)));
        listOfMockValutaHistories.add(new ValutaHistory("01/05/2021", new Rate("USD", 6.22)));

        // Add mock data rates.
        listOfMockValutaHistories.add(new ValutaHistory("01/02/2021", new Rate("CNY", 0.97)));
        listOfMockValutaHistories.add(new ValutaHistory("01/03/2021", new Rate("CNY", 0.93)));
        listOfMockValutaHistories.add(new ValutaHistory("01/04/2021", new Rate("CNY", 0.90)));
        listOfMockValutaHistories.add(new ValutaHistory("01/05/2021", new Rate("CNY", 0.94)));

        this.historicalRates = listOfMockValutaHistories;
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
     * Returns the historical rates, from provided Rate object, as ArrayList<ValutaHistory>.
     *
     * @param rate The valuta rate of whcih historical data is needed.
     * @return List<ValutaHistory> Containing historical data.
     */
    public ArrayList<ValutaHistory> getHistoricalRates(Rate rate) {

        ArrayList<ValutaHistory> selectedValutaHistories = new ArrayList<>();

        for (ValutaHistory history : this.historicalRates) {
            if (rate.getName().equals(history.getValutaRate().getName())) {
                selectedValutaHistories.add(history);
            }
        }

        return selectedValutaHistories;
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

    @Override
    public Double getExchanged(Rate rateFrom, Rate rateTo, Double amount) {

        double valueToExchange;
        double valueExchangeRate;
        double valueResult;

        valueToExchange = amount;

        if(rateFrom.getCurrentRate() < rateTo.getCurrentRate()){
            valueExchangeRate = rateTo.getCurrentRate();
            valueResult = valueToExchange / valueExchangeRate;
        }else{
            valueExchangeRate = rateFrom.getCurrentRate();
            valueResult = valueToExchange * valueExchangeRate;
        }

        return valueResult;
    }

    @Override
    public Rate getOriginRate() {
        return this.getRateByName(ORIGIN_CURRENCY);
    }

}
