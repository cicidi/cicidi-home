package com.cicidi.home.domain.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicidi on 2/17/2017.
 */
public class Feature {
    List<Item> itemList = new ArrayList<>();

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
