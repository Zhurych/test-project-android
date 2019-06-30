package com.example.t3testapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragmentRecyclerViewAdapter extends RecyclerView.Adapter<FragmentRecyclerViewAdapter.ViewHolder>  {

    private static final String LOG_TAG = "MyLogs";

    private List<UserData> mValues;

    public FragmentRecyclerViewAdapter(List<UserData> mValues) {
        this.mValues = mValues;
    }

    /**
     * Создание новых View и ViewHolder элемента списка, которые впоследствии могут переиспользоваться.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.photo.setImageResource(R.drawable.baseline_account_circle_black_18dp);
        holder.name.setText(mValues.get(position).getName());
        if (mValues.get(position).getState() == -1)
            holder.state.setImageResource(R.drawable.baseline_save_black_18dp);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;
        private TextView name;
        private ImageView state;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(LOG_TAG, " КОНСТРУКТОР КЛАССА ViewHolder");
            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name);
            state = itemView.findViewById(R.id.state);
        }
    }
}
