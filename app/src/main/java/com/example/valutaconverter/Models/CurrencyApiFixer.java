package com.example.valutaconverter.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Class used to communicate with Fixer API endpoints.
 *
 * @author BTO
 * @version 1.0
 * @since 1.0
 */
public class CurrencyApiFixer {

    // essential URL structure is built using constants
    public static final String ACCESS_KEY = "API_KEY_HERE";
    public static final String BASE_URL = "http://data.fixer.io/api/";

    public static final String INCLUDED_CURRENCIES = "USD,EUR,DKK,CNY,SEK,NOK,BTC,CAD";

    private ArrayList<Rate> currencyRates;

    /**
     * Returns available rates as ArrayList.
     *
     * @return ArrayList<Rate> over avaiable rates.
     */
    public ArrayList<Rate> getCurrencyRates() {

        if(currencyRates == null){
            ArrayList<Rate> listOfRates = new ArrayList<>();

            try{
                String urlParams = "&symbols=" + INCLUDED_CURRENCIES;
                String endpoint = "latest";

                // Set url string with params.
                String urlString = BASE_URL + endpoint + "?access_key=" + ACCESS_KEY + urlParams;

                //Using the JSON simple library parse the string into a json object
                JSONObject data_obj = JsonApiCall.getJsonFromAPI(urlString);

                JSONObject ratesJson = data_obj.getJSONObject("rates");
                Iterator<String> ratesKeys = ratesJson.keys();

                // Convert rate api to hashmap <String, String>
                HashMap<String, Double> rateHashMap = new HashMap<String, Double>();

                // Iterate through ratesKeys to create hashmap
                while( ratesKeys.hasNext() ){
                    try{
                        String key = (String)ratesKeys.next();
                        Double value = ratesJson.getDouble(key);
                        rateHashMap.put(key, value);
                    }catch(Exception e){
                        // The value could not be added - Continue with the rest anyway
                        // Could log error...
                    }
                }


                for (Map.Entry<String, Double> ratePair : rateHashMap.entrySet()) {

                    listOfRates.add(new Rate(ratePair.getKey(), ratePair.getValue()));
                }

                this.currencyRates = listOfRates;
            }
            catch (Exception e){
                this.currencyRates = null;
            }
        }


        return this.currencyRates;

    }

    /**
     * Returns historical rate data from requested Rate and Date.
     *
     * @param requestedRate The requested Rate object.
     * @param date The Date which the historcal data is requested.
     * @return ValutaHistory object with Rate data from requested date.
     */
    public ValutaHistory getHistoricalData(Rate requestedRate, Date date){
        ValutaHistory historicalData;
        String valutaCode = requestedRate.getName().toUpperCase();

        try{
            // Setup date format.
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormatted = dateFormat.format(date); //2013-03-16

            // Setup URL
            String urlParams = "&symbols=" + valutaCode;
            String endpoint = dateFormatted;

            // Set url string with params.
            String urlString = BASE_URL + endpoint + "?access_key=" + ACCESS_KEY + urlParams;

            //Using the JSON simple library parse the string into a json object
            JSONObject data_obj = JsonApiCall.getJsonFromAPI(urlString);
            JSONObject ratesJson = data_obj.getJSONObject("rates");

            // Rate at date
            Double rateAtRequestedDate = ratesJson.getDouble(valutaCode);

            // Create ValutaHistory from historical data.
            historicalData = new ValutaHistory(dateFormatted, new Rate(valutaCode, rateAtRequestedDate));

        }catch (Exception e){
            historicalData = null;
        }


        return historicalData;
    }
}
