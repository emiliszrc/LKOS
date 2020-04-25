package com.example.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferenceController extends AppCompatActivity {
        private SharedPreferences pref;
        private SharedPreferences.Editor editor;

    public void saveToken (String token){
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
        if (pref.getString("Token", "222")!=null){
            editor.remove("token");
            editor.putString("token","token");
        }

        editor.commit();
    }
    public String returnToken(){
        String token = pref.getString("token", null);
        if (token ==null)
            return "This bitch empty";
        return token;
    }

}
