package com.ppjun.android.ithome.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ppjun.android.ithome.R;

/**
 * Package :com.ppjun.android.ithome
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 15:25.
 */

public class GlideUtil {

    public static void displayImage(String url, ImageView imageView, boolean isCircle) {

        displayImage(R.drawable.ic_phone_iphone_black_24dp, url, imageView, isCircle);


    }


    public static void displayImage(int Ids, String url, ImageView imageView, boolean isCircle) {
        if (imageView.getContext() == null) {
            throw new IllegalArgumentException("content is null");

        } else {
            if (isCircle) {
                Glide.with(imageView.getContext())
                        .load(url)

                        .transform(new GlideRoundTransform(imageView.getContext(), 10))

                        .placeholder(Ids)
                        .into(imageView);

            } else {
                Glide.with(imageView.getContext()).load(url).placeholder(Ids).into(imageView);
            }


        }


    }


    public static void displayImage(String url, ImageView imageView) {
        if (imageView.getContext() == null) {
            throw new IllegalArgumentException("content is null");

        } else {

            Glide.with(imageView.getContext())
                    .load(url)
                    .transform(new GlideRoundTransform(imageView.getContext(), 1))
                    .override(580, 460)
                    .placeholder(R.drawable.ic_phone_iphone_black_24dp)
                    .into(imageView);


        }


    }
}
