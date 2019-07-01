package com.example.t3testapp;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Интерфейс для работы с Retrofit
 * содержит методы запроса
 */
public interface ServerApi {

    @GET("search/users")
    Call<Users> getUsers(
            @Query("q") String userName
    );

}
