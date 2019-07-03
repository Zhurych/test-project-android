package com.example.t3testapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3testapp.databinding.RecyclerViewItemBinding;

import java.util.List;

import io.reactivex.Observable;

/**
 * Адаптер для RecyclerView основанный на DataBinding
 */
public class FragmentRecyclerViewAdapter extends RecyclerView.Adapter<FragmentRecyclerViewAdapter.UserDataHolder>  {

    private static final String LOG_TAG = "MyLogs";

    private List<UserData> mValues;

    public FragmentRecyclerViewAdapter(List<UserData> mValues) {
        this.mValues = mValues;
    }

    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */
    @Override
    public UserDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerViewItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_item, parent, false);
        return new UserDataHolder(binding);
    }

    /**
     * Заполнение виджетов View данными из элемента списка mValues, с индексом = position
     * передача объектов в биндинг
     */
    @Override
    public void onBindViewHolder(UserDataHolder holder, int position) {
        holder.bind(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class UserDataHolder extends RecyclerView.ViewHolder {
        // Биндинг, основанный на имени Layout (а именно: R.layout.recycler_view_item)
        RecyclerViewItemBinding binding;

        // Конструктор принимает биндинг и передает родителю его корневой View
        public UserDataHolder(RecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            Log.d(LOG_TAG, " КОНСТРУКТОР КЛАССА ViewHolder");
        }

        public void bind(UserData userData) {
            // Установка биндинга (передача в него объекта userData)
            binding.setUserData(userData);
            // Используется для того, что бы биндинг выполинлся как можно скорее
            binding.executePendingBindings();
        }
    }
}
