package com.ivann.applicationtvgoodgit.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RoomUtils {

    @TypeConverter
    public static ArrayList<String> restoreList(String listOfString) {
        return new Gson().fromJson(listOfString, new TypeToken<ArrayList<String>>() {}.getType());
    }

    @TypeConverter
    public static String saveList(ArrayList<String> listOfString) {
        return new Gson().toJson(listOfString);
    }
}
