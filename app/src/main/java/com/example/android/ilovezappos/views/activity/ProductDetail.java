package com.example.android.ilovezappos.views.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.databinding.ActivityProductDetailBinding;
import com.example.android.ilovezappos.model.POJO.Product;
import com.example.android.ilovezappos.model.ViewModel.ProductDetailViewModel;

public class ProductDetail extends AppCompatActivity {
    public final static String PRODUCT_INFO = "PRODUCT_INFO";
    private ActivityProductDetailBinding binding; // bind to activity_product_detail.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize layout: activity_product_detail.xml
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        //get serializable product info from intent
        getProductInfo();

        //show home arrow
        showHomeArrow();
    }

    /**
     * static class call when item is cliked: call onSelected(view) in ProductItemViewModel
     * start intent to open activity_product_detail with selected product info
     *
     * @param context
     * @param product - reference got from ProductItemViewModel
     */
    public static void startIntent(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetail.class);
        intent.putExtra(PRODUCT_INFO, product);
        context.startActivity(intent);
    }

    /**
     * get serialize selected product from intent
     */
    private void getProductInfo() {
        Product people = (Product) getIntent().getSerializableExtra(PRODUCT_INFO);
        ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel(this, people);
        binding.setProductDetailViewModel(productDetailViewModel);
        setTitle(people.getProductName());//set title with the name of the product
    }

    /**
     * show home arrow key
     */
    private void showHomeArrow() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * when home arrow is clicked, go back to previous activity
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
