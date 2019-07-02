package com.example.t3testapp;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Класс для получения данных по API
 * при помощи Retrofit2 и RxJava2
 */
public class NetworkService {

    private static final String BASE_URL = "https://api.github.com/";

    private static Retrofit mRetrofit = null;

    private NetworkService() {}

    // Метод будет запускаться в другом потоке (AndroidSchedulers.mainThread())
    public static Retrofit getClient() {
        if (mRetrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                    .build();

            mRetrofit = new Retrofit.Builder()
                    // Добавляем конвертер
                    .addConverterFactory(GsonConverterFactory.create())
                    // Добавляем RxJava2
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .baseUrl(BASE_URL)

                    .client(okHttpClient)

                    .build();
        }

        return mRetrofit;
    }
}
