package com.example.valutaconverter.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class JsonApiCall {

    public static JSONObject getJsonFromAPI(String urlEndpoint) throws IOException, JSONException, RuntimeException {

        String urlString = urlEndpoint;

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

            return data_obj;
        }
    }

}
