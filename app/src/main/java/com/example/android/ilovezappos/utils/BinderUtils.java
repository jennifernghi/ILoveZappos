package com.example.android.ilovezappos.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.ilovezappos.model.POJO.Product;


/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public final class BinderUtils {

    private BinderUtils() {

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
