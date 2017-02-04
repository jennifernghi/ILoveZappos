package com.example.android.ilovezappos.views.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.model.Product;
import com.example.android.ilovezappos.networking.ProductLoader;
import com.example.android.ilovezappos.utils.Constants;
import com.example.android.ilovezappos.views.Adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Product>> {

    private String term = "";
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        startLoading(1);

         adapter = new ProductAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new ProductLoader(getApplicationContext(), Constants.BASE_URL, term);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Product>> loader, ArrayList<Product> data) {
        products = data;
       adapter.setLoadedProducts(products);
        //adapter.notifyDataSetChanged();
    }





    @Override
    public void onLoaderReset(Loader loader) {

    }

    public void startLoading(int loaderConstant) {

        getLoaderManager().restartLoader(loaderConstant, null, ProductActivity.this);


    }
}
