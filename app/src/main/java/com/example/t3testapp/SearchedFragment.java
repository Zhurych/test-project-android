package com.example.t3testapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
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
import android.widget.TextView;

import com.example.t3testapp.databinding.FragmentSearchedBinding;

public class SearchedFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchViewModel mSearchViewModel;

    private static final String LOG_TAG = "MyLogs";

    public RecyclerView recyclerView;

    public SearchedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSearchedBinding binding = FragmentSearchedBinding.bind(inflater.inflate(R.layout.fragment_searched, container, false));

        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        binding.setMSearchViewModel(mSearchViewModel);

        recyclerView = binding.getRoot().findViewById(R.id.searched_rec_view);

        // Нужно установить LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        recyclerView.setAdapter(mSearchViewModel.fragmentRecyclerViewAdapter.get());

        // Говорим фрагменту, что ему нужно отобразить меню
        setHasOptionsMenu(true);

        return binding.getRoot();
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
        mSearchViewModel.searchUsers(s);

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
