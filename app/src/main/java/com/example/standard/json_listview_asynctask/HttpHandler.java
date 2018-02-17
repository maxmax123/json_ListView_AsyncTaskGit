package com.example.standard.json_listview_asynctask;

/**
 * V1.0.0 Created on 07.02.2018
 * Modifications
 *  V
 */

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
        /*
        return "{\n" +
                "    \"contacts\": [\n" +
                "        {\n" +
                "                \"id\": \"c200\",\n" +
                "                \"name\": \"Ravi Tamada\",\n" +
                "                \"email\": \"ravi@gmail.com\",\n" +
                "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                "                \"gender\" : \"male\",\n" +
                "                \"phone\": {\n" +
                "                    \"mobile\": \"+91 0000000000\",\n" +
                "                    \"home\": \"00 000000\",\n" +
                "                    \"office\": \"00 000000\"\n" +
                "                }\n" +
                "        },\n" +
                "        {\n" +
                "                \"id\": \"c201\",\n" +
                "                \"name\": \"Johnny Depp\",\n" +
                "                \"email\": \"johnny_depp@gmail.com\",\n" +
                "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                "                \"gender\" : \"male\",\n" +
                "                \"phone\": {\n" +
                "                    \"mobile\": \"+91 0000000000\",\n" +
                "                    \"home\": \"00 000000\",\n" +
                "                    \"office\": \"00 000000\"\n" +
                "                }\n" +
                "        }]}";
        */
    }
}
