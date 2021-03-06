package com.example.android.ilovezappos.utils;

import android.content.Context;
import android.net.Uri;

import com.example.android.ilovezappos.model.POJO.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by jennifernghinguyen on 2/3/17.
 * this immutable class is responsible for handling:
 * url string modifications
 * parsing json
 * main fetch data from url
 */

public final class URLUtils {
    public static Context context;

    private URLUtils() {

    }

    /**
     * build a valid url from url base and search term
     *
     * @param ct
     * @param urlBase
     * @param term
     * @return url string
     */
    public static String buildURI(Context ct, String urlBase, String term) {
        context = ct;
        if (urlBase == null && term == null) {
            return null;
        }

        Uri base = Uri.parse(urlBase);
        Uri.Builder builder = base.buildUpon();
        builder.appendQueryParameter("key", Constants.API_KEY);
        builder.appendQueryParameter("term", term);
        return builder.toString();
    }

    /**
     * create an URL object out of valid urlString
     *
     * @param urlString
     * @return URL
     */
    private static URL createURL(String urlString) {
        URL url = null;
        if (urlString == null) {
            return null;
        }

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {

        }

        return url;
    }

    /**
     * open httpurlconnection and download json response
     *
     * @param url
     * @return json response
     * @throws IOException
     */
    private static String downloadJsonResponse(URL url) throws IOException {
        String response = "";
        if (url == null) {
            return response;
        }

        //open HTTP connection
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(6000);
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                response = getResponseFromStream(inputStream);
            }
        } catch (IOException e) {

        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
        return response;
    }

    /**
     * read json response from input stream and write to a string
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String getResponseFromStream(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                response.append(line);
                line = reader.readLine();
            }
        }

        return response.toString();
    }

    /**
     * extract data from downloaded json response
     *
     * @param jsonResponse
     * @return ArrayList<Product>
     */
    private static ArrayList<Product> extractProducts(String jsonResponse) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray results = root.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject product = (JSONObject) results.get(i);
                //brand name
                String brandName = getString(product, "brandName");

                //thumbnailImageUrl
                String thumbnailImageUrl = getString(product, "thumbnailImageUrl");

                //originalPrice
                String originalPrice = getString(product, "originalPrice");

                //price
                String price = getString(product, "price");

                //percentOff
                String percentOff = getString(product, "percentOff");

                //productName
                String productName = getString(product, "productName");

                if (brandName != null && thumbnailImageUrl != null && originalPrice != null && price != null && percentOff != null && productName != null) {
                    //create the product
                    products.add(new Product(brandName, thumbnailImageUrl, originalPrice, price, percentOff, productName));
                }
            }
        } catch (JSONException e) {

        }

        return products;

    }

    /**
     * get string from Json type string
     *
     * @param object - Json object
     * @param string - string inside Json object
     * @return string
     */
    private static String getString(JSONObject object, String string) {
        String str = null;

        try {
            str = object.getString(string);
        } catch (JSONException e) {

        }

        if (str != null) {
            return str;
        } else {
            return "";
        }
    }

    /**
     * fetch data from urlString
     *
     * @param urlString
     * @return
     */
    public static ArrayList<Product> fetchData(String urlString) {
        ArrayList<Product> news = new ArrayList<>();
        URL url = createURL(urlString);

        String response = "";
        try {
            response = downloadJsonResponse(url);
        } catch (IOException e) {

        }

        if (!response.equals("")) {
            news = extractProducts(response);
        }

        return news;

    }
}
