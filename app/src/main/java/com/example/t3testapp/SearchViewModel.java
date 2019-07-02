package com.example.t3testapp;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {
    public static final String LOG_TAG = "MyLogs";

    private ArrayList<Item> allCurrencyList;

    private ArrayList<UserData> listUsersData;

    private Observable<UserData> data;

    public ArrayList<Item> start() {

        ServerApi serverApi = NetworkService.getClient().create(ServerApi.class);
        // Здесь должно быть имя пользователя
        StringBuilder name = new StringBuilder();

        name.append("search/users?q=bob+in:name");
        Log.d(LOG_TAG, name.toString());
        serverApi.getUsers(name.toString())
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<Users>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Users users) {
                        allCurrencyList = new ArrayList<>(users.getItems());
                        Log.d(LOG_TAG, "Список юзеров:" + allCurrencyList.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "Ошибка " + e);
                    }

                    @Override
                    public void onComplete() {
                        // Updates UI with data
                    }
                });

        return allCurrencyList;
    }

    public ArrayList<UserData> searchUsers(ArrayList<Item> items) {
        listUsersData = new ArrayList<>(30);
        ServerApi serverApi = NetworkService.getClient().create(ServerApi.class);

        for (int i = 0; i < items.size(); i++) {
            serverApi.getUser(items.get(i).getLogin())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UserData>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(UserData userData) {
                            listUsersData.add(userData);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        return listUsersData;
    }
}
