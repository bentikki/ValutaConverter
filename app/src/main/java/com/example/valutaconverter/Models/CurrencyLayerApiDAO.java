package com.example.valutaconverter.Models;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CurrencyLayerApiDAO implements CurrencyDAO{


    private final String ORIGIN_CURRENCY = "EUR";
    private ArrayList<Rate> currencyRates;
    private CurrencyApiFixer currencyAPI = new CurrencyApiFixer();


    // essential URL structure is built using constants
    public static final String ACCESS_KEY = "123871fec0079d5c098c46f8cb30c6cb";
    public static final String BASE_URL = "http://data.fixer.io/api/";
    public static final String ENDPOINT = "latest";
    public static final String URL_PARAMS = "&symbols=USD,EUR,DKK,CNY,SEK,NOK,BTC,CAD";

    @Override
    public ArrayList<Rate> getCurrentRates() {

        try{

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        currencyAPI.getCurrencyRates();
                    } catch (Exception e) {
                        // An error has occured.
                    }
                }
            });

            // Call currency api thread
            thread.start();

            // Wait for API to respond.
            thread.join();

            // Set api rate result to this.currencyRates
            this.currencyRates = currencyAPI.getCurrencyRates();

            if(this.currencyRates == null) throw new NullPointerException();

            Rate originRate = this.getOriginRate();
            this.currencyRates.remove(originRate);
            this.currencyRates.add(0, originRate);

            return this.currencyRates;

        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Rate getRateByName(String valutaName) {

        for (Rate valutaRate : this.currencyRates) {
            if (valutaRate.getName().equals(valutaName)) {
                return valutaRate;
            }
        }

        return null;
    }

    @Override
    public ArrayList<ValutaHistory> getHistoricalRates(Rate rate) {

        ArrayList<ValutaHistory> historicalData = new ArrayList<>();
        ArrayList<Date> requestedDates = new ArrayList<Date>();

        // Number of months in the past
        int numberOfMonths = 3;

        // Adds the past months to requestedDates as Date objects.
        for (int i = 1; i <= numberOfMonths; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -i);
            requestedDates.add(cal.getTime());
        }

        for (Date requestedDate:
             requestedDates) {

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Callable<ValutaHistory> callable = new Callable<ValutaHistory>() {
                @Override
                public ValutaHistory call() {
                    return currencyAPI.getHistoricalData(rate, requestedDate);
                }
            };
            Future<ValutaHistory> future = executor.submit(callable);

            try {
                historicalData.add(future.get());
                executor.shutdown();
            } catch (ExecutionException | InterruptedException e) {

            }
        }

        return historicalData;
    }

    @Override
    public Double getExchanged(Rate rateFrom, Rate rateTo, Double amount) {

        double valueExchangeRate;
        double valueResult;

        if(rateFrom.getCurrentRate() == 1){
            valueExchangeRate = rateTo.getCurrentRate();
            valueResult = amount * valueExchangeRate;
        }else{
            valueExchangeRate = rateFrom.getCurrentRate();
            valueResult = amount / valueExchangeRate;
        }

        return valueResult;

    }

    @Override
    public Rate getOriginRate() {
        return this.getRateByName(ORIGIN_CURRENCY);
    }
}
