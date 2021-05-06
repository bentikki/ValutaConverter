package com.example.valutaconverter.Models;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CurrencyLayerAPI implements CurrencyDAO{


    // essential URL structure is built using constants
    public static final String ACCESS_KEY = "ad4590a141663e8a1f5fcaec7d7da146";
    public static final String BASE_URL = "http://api.currencylayer.com/";
    public static final String ENDPOINT = "live";
    public static final String URL_PARAMS = "";

    @Override
    public ArrayList<Rate> getCurrentRates() {

        ArrayList<Rate> listOfMockRates = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Boolean success = false;

                try  {
                    String urlString = BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + URL_PARAMS;

                    URL url = new URL(urlString);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();

                    //Getting the response code
                    int responsecode = conn.getResponseCode();

                    if (responsecode != 200) {
                        throw new RuntimeException("HttpResponseCode: " + responsecode);
                    } else {

                        String inline = "";
                        Scanner scanner = new Scanner(url.openStream());

                        //Write all the JSON data into a string using a scanner
                        while (scanner.hasNext()) {
                            inline += scanner.nextLine();
                        }

                        //Close the scanner
                        scanner.close();

                        //Using the JSON simple library parse the string into a json object
                        JSONObject data_obj = new JSONObject(inline);

                        //Get the required object from the above created object
                        success = data_obj.getBoolean("success");

                        String currencyName = data_obj.getString("source");

                        JSONArray rateArrayJSON =  data_obj.getJSONArray("quotes");

                        for (int i = 0; i < rateArrayJSON.length(); i++) {
                            JSONObject obj = rateArrayJSON.getJSONObject(i);

                            listOfMockRates.add(new Rate(obj.toString(), 1.00));

                        }

                        // Add mock data rates.
                        listOfMockRates.add(new Rate(currencyName, 7.44));
                        listOfMockRates.add(new Rate(currencyName, 6.20));
                        listOfMockRates.add(new Rate("CNY", 0.96));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try{
            thread.join();

            return listOfMockRates;

        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Rate getRateByName(String valutaName) {
        return null;
    }

    @Override
    public ArrayList<ValutaHistory> getHistoricalRates(Rate rate) {
        return null;
    }

    @Override
    public Double getExchanged(Rate rateFrom, Rate rateTo, Double amount) {
        return null;
    }
}
