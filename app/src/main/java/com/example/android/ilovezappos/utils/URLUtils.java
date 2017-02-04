package com.example.android.ilovezappos.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.model.Product;

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

import static kotlin.text.Typography.section;

/**
 * Created by jennifernghinguyen on 2/3/17.
 * this clas is responsible for handling:
 * url string modifications
 */

public final class URLUtils {
    final static String LOG_TAG = URLUtils.class.getSimpleName();
    public static Context context;
    private URLUtils(){

    }

    public static String buildURI(Context ct, String urlBase, String term){
        context = context;
        if(urlBase == null && term==null){
            return null;
        }

        Uri base = Uri.parse(urlBase);
        Uri.Builder builder = base.buildUpon();
        builder.appendQueryParameter("key", Constants.API_KEY);
        builder.appendQueryParameter("term",term);
        return builder.toString();
    }

    public static URL createURL(String urlString){
        URL url = null;
        if(urlString == null){
            return null;
        }

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "createURL(): error create url");
        }

        return url;
    }

    public static String downloadJsonResponse(URL url) throws IOException {
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
            Log.e(LOG_TAG, "downloadJsonResponse(): error making connection");
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

    public static ArrayList<Product> extractProducts(String jsonResponse){
        ArrayList<Product> products = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray results = root.getJSONArray("results");

            for(int i =0; i< results.length(); i++){

                JSONObject product = (JSONObject) results.get(i);
                String brandName = getString(product,"brandName");
                String thumbnailImageUrl = getString(product,"thumbnailImageUrl");
                String originalPrice = getString(product,"originalPrice");
                String price = getString(product,"price");
                String percentOff = getString(product,"percentOff");
                String productUrl = getString(product,"productUrl");
                String productName = getString(product,"productName");

                if(brandName!=null && thumbnailImageUrl!=null && originalPrice!=null && price!=null && percentOff !=null && productUrl!=null && productName !=null){
                    products.add(new Product(brandName, thumbnailImageUrl, originalPrice, price, percentOff, productUrl, productName));
                }
            }
        } catch (JSONException e) {
           Log.e(LOG_TAG, "getResponseFromStream(): error parsing json");
        }

        return products;

    }

    private static String getString(JSONObject object, String string) {
        String str = null;

        try {
            str = object.getString(string);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "product() error extract" + string);
        }

        if (str != null) {
            return str;
        } else {
            return "";
        }
    }

    public static ArrayList<Product> fetchData(String urlString) {
        ArrayList<Product> news = new ArrayList<>();
        URL url = createURL(urlString);

        String response = "";
        try {
            response = downloadJsonResponse(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "downloadJsonResponse(): error making connection");
        }

        if (!response.equals("")) {
            news = extractProducts(response);
        }

        return news;

    }
}
