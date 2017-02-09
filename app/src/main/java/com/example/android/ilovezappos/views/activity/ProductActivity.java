package com.example.android.ilovezappos.views.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.databinding.ActivityProductBinding;
import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.model.ViewModel.ProductActivityViewModel;
import com.example.android.ilovezappos.networking.ProductLoader;
import com.example.android.ilovezappos.utils.Constants;
import com.example.android.ilovezappos.views.Adapter.ProductAdapter;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Product>> {


    private String term = ""; // hold search words
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    private ActivityProductBinding binding; //bind to activity_product.xml
    private ProductActivityViewModel productActivityViewModel; //viewmodel class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflate layout, views...
        initializeView();
        //download data from url
        startLoading(Constants.LOADER_CONSTANT);
    }

    /**
     * initialize all views
     */
    private void initializeView() {
        initializeLayoutBinding();
        initializeRecyclerView(productActivityViewModel.getRecyclerView());
        initializeEmptyView(productActivityViewModel.getEmptyView());
        implementSearchBox(productActivityViewModel.getEditField());
        productActivityViewModel.showProgressBar(false);
    }


    /**
     * set up bindings: activity_product.xml and it's view model ProductActivityViewModel class
     */
    private void initializeLayoutBinding() {
        //initialize binding with activity_product.xml
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        //hook view model with activity_product.xml binding
        productActivityViewModel = new ProductActivityViewModel(this, binding);
        binding.setProductActivityViewModel(productActivityViewModel);
    }

    /**
     * initialize recycler view
     *
     * @param recyclerView - reference from view model: productActivityViewModel
     */
    private void initializeRecyclerView(RecyclerView recyclerView) {
        adapter = new ProductAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    /**
     * initialize empty view
     *
     * @param relativeLayout - reference from view model: productActivityViewModel
     */
    private void initializeEmptyView(RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(View.GONE);
    }

    /**
     * implement search box and search feature
     *
     * @param searchInput - editText from view model: productActivityViewModel
     */
    private void implementSearchBox(final EditText searchInput) {
        searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchInput.getText().length() < 1) {
                    searchInput.setHint(getResources().getString(R.string.search));
                    searchInput.setText("");
                }
            }
        });
        //search feature
        search(searchInput);
    }

    /**
     * initialize Asynstaskloader class: ProductLoader
     *
     * @param id
     * @param args
     * @return ProductLoader
     */
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new ProductLoader(getApplicationContext(), Constants.BASE_URL, term);
    }

    /**
     * update UI after finish loading
     *
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(Loader<ArrayList<Product>> loader, ArrayList<Product> data) {
        if (data.size() >= 1) {
            productActivityViewModel.enableEmptyView(false, null);
            products = data;
            adapter.setLoadedProducts(products);
            productActivityViewModel.showProgressBar(false);

        } else {
            //show empty view if fail
            productActivityViewModel.enableEmptyView(true, getString(R.string.no_data));
        }
    }

    /**
     * search feature: update as user input words
     *
     * @param editText
     */
    private void search(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                term = s.toString().trim(); //update search term
                startLoading(Constants.LOADER_CONSTANT);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * when loader reset
     *
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader loader) {
        loader.reset();
    }

    /**
     * start loading data from url
     * only when having internet connection
     *
     * @param loaderConstant
     */
    public void startLoading(int loaderConstant) {
        if (checkNetWorkConnection()) {
            productActivityViewModel.showProgressBar(true);
            getLoaderManager().restartLoader(loaderConstant, null, ProductActivity.this);
        } else {
            //if no internet connection show empty view
            productActivityViewModel.enableEmptyView(true, getString(R.string.no_network));
        }
    }

    /**
     * check internet connection
     * @return true/false
     */
    private boolean checkNetWorkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

}
