package com.example.android.ilovezappos.views.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android.ilovezappos.Controller.ProductDetailController;
import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.databinding.ActivityProductDetailBinding;
import com.example.android.ilovezappos.model.POJO.Product;

import static com.example.android.ilovezappos.utils.URLUtils.context;

public class ProductDetail extends AppCompatActivity {
    public final static String PRODUCT_INFO = "PRODUCT_INFO";
    ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        getProductInfo();
        showHomeArrow();
    }

    public static void startIntent(Context context, Product product){
        Intent intent = new Intent(context, ProductDetail.class);
        intent.putExtra(PRODUCT_INFO, product);
        context.startActivity(intent);
    }
    private void getProductInfo() {
        Product people = (Product) getIntent().getSerializableExtra(PRODUCT_INFO);
        ProductDetailController productDetailController = new ProductDetailController(this,people);
        binding.setProductDetailController(productDetailController);
        setTitle(people.getProductName());
    }

    private void showHomeArrow() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
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
