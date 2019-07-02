package com.example.t3testapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;


import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public class SearchedFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchViewModel mSearchViewmodel;

    private ArrayList<Item> listItem;

    private ArrayList<UserData> list;

    private static final String LOG_TAG = "MyLogs";

    private OnFragmentInteractionListener mListener;

    private List<Item> allCurrencyList;

    public SearchedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_searched, container, false);

        mSearchViewmodel = ViewModelProviders.of(this).get(SearchViewModel.class);
        listItem = new ArrayList<>();
        list = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.searched_rec_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ArrayList<UserData> list = new ArrayList<>();
        for (int i = 1; i < 20; i++)
            list.add(new UserData("Заключенный № " + i));
        recyclerView.setAdapter(new FragmentRecyclerViewAdapter(list));

        // Говорим фрагменту, что ему нужно отобразить меню
        setHasOptionsMenu(true);

        // Здесь должно быть имя пользователя
        String name = "zhurych" + "+in:name";

        return view;
    }

    /**
     * Создание мени поиска
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(LOG_TAG, "МЕТОД onCreateOptionsMenu");
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    /**
     * Вызывается, когда будет нажата кнопка поиска.
     */
    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.d(LOG_TAG, "МЕТОД onQueryTextSubmit. Нажата кнопка поиска");
        listItem = mSearchViewmodel.start();

        //list = mSearchViewmodel.searchUsers(listItem);

        return true;
    }

    /**
     * Вызывается после ввода пользователем каждого символа в текстовом поле.
     */
    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
