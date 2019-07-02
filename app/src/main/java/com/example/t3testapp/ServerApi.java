package com.example.t3testapp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Интерфейс для работы с Retrofit
 * содержит методы запроса
 */
public interface ServerApi {

    @GET()
    Observable<Users> getUsers(
            @Url String url
    );

    @GET("users/{login}")
    Observable<UserData> getUser(
            @Path("login") String login
    );

}
