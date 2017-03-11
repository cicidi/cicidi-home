package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wchen00 on 3/8/17.
 */
public class Album {
    private List<Item> itemList;

    public Album(Profile profile) {
        this.itemList = new ArrayList<>();
        itemList.addAll(profile.getWorkExperienceList().stream().map(Item::new).collect(Collectors.toList()));
        itemList.addAll(profile.getEducationList().stream().map(Item::new).collect(Collectors.toList()));

    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
