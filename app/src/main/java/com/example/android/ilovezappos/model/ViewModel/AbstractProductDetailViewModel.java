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

/**
 * abstact super class
 * view model for ProductItemViewModel and ProductDetailViewModel
 */
public abstract class AbstractProductDetailViewModel {
    private Context context;
    //reference of the selected product
    private Product product;

    public AbstractProductDetailViewModel(Context _context, Product _product) {
        context = _context;
        product = _product;
    }

    /**
     * get the product
     * @return product object
     */
    public Product getProduct() {
        return product;
    }

    /**
     * get prpduct's brand name
     * @return string
     */
    public String getBrandName() {
        return product.getBrandName();
    }


    /**
     * get prpduct's thumbnailimageurl
     * @return string
     */
    public String getThumbnailImageUrl() {
        return product.getThumbnailImageUrl();
    }

    /**
     * get prpduct's original price
     * @return string
     */
    public String getOriginalPrice() {
        return product.getOriginalPrice();
    }


    /**
     * get prpduct's price
     * @return string
     */
    public String getPrice() {
        return product.getPrice();
    }

    /**
     * get prpduct's percent off
     * @return string
     */
    public String getPercentOff() {
        return product.getPercentOff() + " " + context.getString(R.string.off);
    }

    /**
     * get prpduct's name
     * @return string
     */
    public String getProductName() {
        return product.getProductName();
    }

    /**
     * get context
     * @return context
     */
    public Context getContext() {
        return context;
    }

    /**
     * for each image view use Glide to load image to image view with tag app:thumbnailImageUrl
     * @param imageView
     * @param thumbnailImageUrl
     */
    @BindingAdapter("thumbnailImageUrl")
    public static void setImages(ImageView imageView, String thumbnailImageUrl) {
        Context context = imageView.getContext();
        Glide.with(context).load(thumbnailImageUrl).into(imageView);
    }

    /**
     * for each text view with tag app: strikethrough
     * the text has a strike through
     * @param textView
     * @param text
     */
    @BindingAdapter("strikethrough")
    public static void setStrikeThroughText(TextView textView, String text) {
        textView.setText(text);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    /**
     * for each text view with tag app:orginalPriceVisibility
     * if the product has a positive discount, set it to be visible
     * @param textView
     * @param product
     */
    @BindingAdapter("orginalPriceVisibility")
    public static void setorginalPriceVisibility(TextView textView, Product product) {
        if (product.hasDiscount() && !product.getPercentOff().startsWith("-")) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}
