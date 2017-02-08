package com.example.android.ilovezappos.model.ViewModel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    @BindingAdapter("thumbnailImageUrl")
    public static void setImages(ImageView imageView, String thumbnailImageUrl) {
        Context context = imageView.getContext();
        Glide.with(context).load(thumbnailImageUrl).into(imageView);
    }

    @BindingAdapter("strikethrough")
    public static void setStrikeThroughText(TextView textView, String text) {
        textView.setText(text);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @BindingAdapter("orginalPriceVisibility")
    public static void setorginalPriceVisibility(TextView textView, Product product) {
        if (product.hasDiscount()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}
