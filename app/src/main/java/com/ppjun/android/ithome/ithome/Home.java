package com.ppjun.android.ithome.ithome;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Package :com.ppjun.android.ithome.ithome
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 14:32.
 */

public class Home {

    private static volatile Home mInstance;
    Retrofit retrofit;

    private Home() {

        retrofit = new Retrofit.Builder()
                .baseUrl(HomeUrl.ANDROIDURL +1+ HomeUrl.URL_END+"/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(ScalarsConverterFactory.create())
                .build();


    }

    public static Home getInstance() {
        if (mInstance == null) {
            synchronized (Home.class) {
                if (mInstance == null) {
                    mInstance = new Home();
                }
            }
        }
        return mInstance;
    }

    public HomeService getService() {
        return retrofit.create(HomeService.class);
    }

}
