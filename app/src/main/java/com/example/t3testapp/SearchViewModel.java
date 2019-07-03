package com.example.t3testapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends AndroidViewModel {
    private static final String LOG_TAG = "MyLogs";

    private ArrayList<UserData> listUsersData;

    public ObservableField<ArrayList<UserData>> data;

    public ObservableField<FragmentRecyclerViewAdapter> fragmentRecyclerViewAdapter;

    // Для доступа к context. Метод getApplication()
    public SearchViewModel(@NonNull Application application) {
        super(application);
        data = new ObservableField<>();
        fragmentRecyclerViewAdapter = new ObservableField<>();
        fragmentRecyclerViewAdapter.set(new FragmentRecyclerViewAdapter(new ArrayList<UserData>()));
        Log.d(LOG_TAG, "КОНСТРУКТОР SearchViewModel");
    }

    public void searchUsers(String s) {

        listUsersData = new ArrayList<>();

        final ServerApi serverApi = NetworkService.getClient().create(ServerApi.class);
        // Здесь должно быть имя пользователя
        StringBuilder name = new StringBuilder();

        String fullName = s.replaceAll(" ", "+");
        name.append("search/users?q=").append(fullName).append("+in:name");
        Log.d(LOG_TAG, name.toString());

        serverApi.getUsers(name.toString())
                .map(new Function<Users, List<Item>>(

                ) {
                    @Override
                    public List<Item> apply(Users users) throws Exception {
                        Log.d(LOG_TAG, "Преобразование Users в List<Items>: " + users.getItems().size());
                        return users.getItems();
                    }
        })
                .flatMap(new Function<List<Item>, ObservableSource<Item>>() {
            @Override
            public ObservableSource<Item> apply(List<Item> items) throws Exception {
                Log.d(LOG_TAG, "Преобзразование List<Item> в Item: " + items);
                return Observable.fromIterable(items);
            }
        })
                .flatMap(new Function<Item, ObservableSource<UserData>>() {
            @Override
            public ObservableSource<UserData> apply(Item item) throws Exception {
                Log.d(LOG_TAG, "Запрос юзера по логину: " + item.getLogin());
                return serverApi.getUser(item.getLogin());
            }
        })
                // Выполнение запросов выполняется в пуле потоков ввода\вывода
                .subscribeOn(Schedulers.io())
                // Результаты будем получать в основном потоке
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<UserData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserData userData) {
                        listUsersData.add(userData);
                        // Сюда приходят объекты юзеров !!!
                        Log.d(LOG_TAG, "Имя пользователя: " + userData.getName());
                        Log.d(LOG_TAG, "Фото url : " + userData.getPhotoId());
                        Log.d(LOG_TAG, "id : " + userData.get_id());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(LOG_TAG, "Полностью завершилась загрузка. Размер списка: " + listUsersData.size());
                        data.set(listUsersData);
                    }
                });

        fragmentRecyclerViewAdapter.set(new FragmentRecyclerViewAdapter(listUsersData));

    }
}
