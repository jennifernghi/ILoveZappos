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

import com.example.android.ilovezappos.model.ViewModel.ProductActivityViewModel;
import com.example.android.ilovezappos.model.ViewModel.ProductItemViewModel;
import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.databinding.ActivityProductBinding;
import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.networking.ProductLoader;
import com.example.android.ilovezappos.utils.Constants;
import com.example.android.ilovezappos.views.Adapter.ProductAdapter;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Product>> {

    private String term = "";
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    private ActivityProductBinding binding;
    private ProductActivityViewModel productActivityViewModel;
    private ProductItemViewModel productItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        productActivityViewModel = new ProductActivityViewModel(this, binding);
        binding.setProductActivityViewModel(productActivityViewModel);
        initializeView();
        startLoading(1);
    }

    private void initializeView() {
        initializeRecyclerView(productActivityViewModel.getRecyclerView());
        initializeEmptyView(productActivityViewModel.getEmptyView());
        implementSearchBox(productActivityViewModel.getEditField());
    }

    private void initializeRecyclerView(RecyclerView recyclerView) {
        adapter = new ProductAdapter(products);
        productItemViewModel = adapter.getProductItemViewModel();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initializeEmptyView(RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(View.GONE);
    }

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
        search(searchInput);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new ProductLoader(getApplicationContext(), Constants.BASE_URL, term);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Product>> loader, ArrayList<Product> data) {
        if (data.size() >= 1) {
            enableEmptyView(false, null);
            products = data;
            adapter.setLoadedProducts(products);

        } else {
            enableEmptyView(true, getString(R.string.no_data));
        }
    }


    public void search(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                term = s.toString().trim();
                startLoading(1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }

    public void startLoading(int loaderConstant) {
        if (checkNetWorkConnection()) {
            getLoaderManager().restartLoader(loaderConstant, null, ProductActivity.this);
        } else {
            enableEmptyView(true, getString(R.string.no_network));
        }
    }


    public void enableEmptyView(boolean status, String message) {
        if (status) {
            productActivityViewModel.getRecyclerView().setVisibility(View.GONE);
            productActivityViewModel.getEmptyView().setVisibility(View.VISIBLE);
            productActivityViewModel.getEmptyTextView().setVisibility(View.VISIBLE);
            productActivityViewModel.getEmptyTextView().setText(message);
        } else {
            productActivityViewModel.getRecyclerView().setVisibility(View.VISIBLE);
            productActivityViewModel.getEmptyView().setVisibility(View.GONE);
            productActivityViewModel.getEmptyTextView().setVisibility(View.GONE);
        }
    }


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
