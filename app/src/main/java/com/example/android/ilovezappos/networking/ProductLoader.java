package com.example.android.ilovezappos.networking;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.utils.Constants;
import com.example.android.ilovezappos.utils.URLUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jennifernghinguyen on 2/3/17.
 */

public class ProductLoader extends AsyncTaskLoader<List<Product>> {
    private String baseUrl;
    private String term;//search term
    private Context context;

    public ProductLoader(Context context, String baseUrl, String term) {
        super(context);
        this.baseUrl = baseUrl;
        this.term = term;
        this.context = context;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Product> loadInBackground() {
        ArrayList<Product> products = new ArrayList<>();
        if (baseUrl == null || !baseUrl.equals(Constants.BASE_URL)) {
            return null;
        }

        if (term == null) {
            term = "";
        }

        //build a valid url from baseurl and search term
        String url = URLUtils.buildURI(context, baseUrl, term);
        if (url != null) {
            //fetch data from the valid url
            products = URLUtils.fetchData(url);
        }


        return products;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(List<Product> data) {
        super.onCanceled(data);
        if (data != null) {
            data.clear();
        }
    }
}
