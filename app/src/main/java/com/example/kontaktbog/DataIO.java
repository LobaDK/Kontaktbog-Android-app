package com.example.kontaktbog;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataIO {
    private final SharedPreferences pref;
    private final Gson gson;


    private static DataIO instance;
    private List<Contact> contacts = new ArrayList<>();


    private DataIO(@NonNull Context context){
        pref = context.getSharedPreferences("Contacts", Context.MODE_PRIVATE);
        gson = new Gson();
        load();
    }

    private void load(){
        String json = pref.getString("Contacts", null);
        if (json != null){
            Type type = new TypeToken<List<Contact>>() {}.getType();
            contacts = gson.fromJson(json, type);
        }
    }

    private void save(){
        String json = gson.toJson(contacts);
        pref.edit().putString("Contacts", json).apply();
    }

    public void addContact(Contact contact){
        contacts.add(contact);
        save();
    }

    public List<Contact> getContacts(){
        return contacts;
    }

    public static DataIO getInstance(Context context) {
        if (instance == null) {
            instance = new DataIO(context);
        }
        return instance;
    }
}
