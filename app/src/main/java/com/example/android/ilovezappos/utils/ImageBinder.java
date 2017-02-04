package com.example.android.ilovezappos.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by jennifernghinguyen on 2/4/17.
 */

public final class ImageBinder {

    private ImageBinder(){

    }

    @BindingAdapter("thumbnailImageUrl")
    public static void setImages(ImageView imageView, String thumbnailImageUrl){
        Context context = imageView.getContext();
        Glide.with(context).load(thumbnailImageUrl).into(imageView);
    }
}
