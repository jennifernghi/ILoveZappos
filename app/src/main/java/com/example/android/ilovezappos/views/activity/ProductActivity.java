package com.example.android.ilovezappos.views.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.model.Product;
import com.example.android.ilovezappos.networking.ProductLoader;
import com.example.android.ilovezappos.utils.Constants;
import com.example.android.ilovezappos.views.Adapter.ProductAdapter;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Product>> {
    final static String LOG_TAG = ProductActivity.class.getSimpleName();
    private String term = "";
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    private EditText searchInput;
    private RelativeLayout emptyView;
    private TextView emptyTextView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        implementSearchBox();
        implementEmptyView();
        recyclerView = (RecyclerView) findViewById(R.id.list);

        startLoading(1);

        adapter = new ProductAdapter(products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void implementEmptyView() {
        emptyView = (RelativeLayout) findViewById(R.id.empty_view);
        emptyTextView = (TextView) findViewById(R.id.empty_text_view);
        emptyView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    private void implementSearchBox() {
        searchInput = (EditText) findViewById(R.id.input_text);
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
        getLoaderManager().restartLoader(loaderConstant, null, ProductActivity.this);
    }


    public void enableEmptyView(boolean status, String message) {
        if (status) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.VISIBLE);
            emptyTextView.setText(message);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.GONE);
        }
    }
}
