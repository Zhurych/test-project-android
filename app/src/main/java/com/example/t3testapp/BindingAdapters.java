package com.example.t3testapp;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter({"app:url", "app:errorImage"})
    public static void loadImage(ImageView view, String url, Drawable errorImage) {
        if (TextUtils.isEmpty(url))
            view.setImageResource(R.drawable.baseline_account_circle_black_18dp);
        else
            Picasso.get().load(url).error(errorImage).into(view);
    }
}
