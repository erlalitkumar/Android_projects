package com.lkb.baseandroidproject;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ImageLoader {
    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return "https://pngimage.net/wp-content/uploads/2018/06/png-small-1.png";
    }

    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
