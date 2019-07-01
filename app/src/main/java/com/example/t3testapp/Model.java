package com.example.t3testapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
