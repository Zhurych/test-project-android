package com.example.t3testapp;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("total_count")
    private Integer totalCount;

    @SerializedName("items")
    private List<Item> items = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
