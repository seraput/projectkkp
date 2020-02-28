package com.android.projectnew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.android.projectnew.Admin.Homepage;
import com.android.projectnew.Admin.LoginAdminActivity;

import java.util.HashMap;

public class SessionManagerAdmin {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String NILAI = "NILAI";
    public static final String NILAI2 = "NILAI2";
    public static final String NILAI3 = "NILAI3";
    public static final String ID = "ID";

    public SessionManagerAdmin(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession (String name, String email, String id){
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(ID, id);
        editor.apply();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){
        if (!this.isLogin()){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            ((Homepage) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(NILAI, sharedPreferences.getString(NILAI, null));
        user.put(NILAI2, sharedPreferences.getString(NILAI2, null));
        user.put(NILAI3, sharedPreferences.getString(NILAI3, null));
        user.put(ID, sharedPreferences.getString(ID, null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginAdminActivity.class);
        context.startActivity(intent);
        ((Read_Profile)context).finish();
    }

}
