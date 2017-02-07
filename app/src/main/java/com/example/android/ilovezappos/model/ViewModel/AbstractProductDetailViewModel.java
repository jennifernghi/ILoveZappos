package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;

import com.example.android.ilovezappos.R;
import com.example.android.ilovezappos.model.POJO.Product;

/**
 * Created by jennifernghinguyen on 2/7/17.
 */

public abstract class AbstractProductDetailViewModel {
    private Context context;
    private Product product;

    public AbstractProductDetailViewModel(Context _context, Product _product) {
        context = _context;
        product = _product;
    }

    public Product getProduct() {
        return product;
    }

    public String getBrandName() {
        return product.getBrandName();
    }


    public String getThumbnailImageUrl() {
        return product.getThumbnailImageUrl();
    }


    public String getOriginalPrice() {
        return product.getOriginalPrice();
    }


    public String getPrice() {
        return product.getPrice();
    }


    public String getPercentOff() {
        return product.getPercentOff() + " " + context.getString(R.string.off);
    }


    public String getProductUrl() {
        return product.getProductUrl();
    }


    public String getProductName() {
        return product.getProductName();
    }

    public Context getContext() {
        return context;
    }
}
