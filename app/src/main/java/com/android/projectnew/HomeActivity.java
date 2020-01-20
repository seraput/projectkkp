package com.android.projectnew;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private TextView name, email;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
//
//        name = findViewById(R.id.name);
//        email = findViewById(R.id.email);

        HashMap<String, String> user = sessionManager.getUserDetail();
//        String mName = user.get(SessionManager.NAME);
//        String mEmail = user.get(SessionManager.EMAIL);

//        name.setText(mName);
//        email.setText(mEmail);

    }

    public void profile(View view) {
        Intent intent = new Intent(HomeActivity.this, Read_Profile.class);
        startActivity(intent);
    }

    public void karir(View view) {
        Intent intent = new Intent(HomeActivity.this, CarrerActivity.class);
        startActivity(intent);
    }

    public void notif(View view) {
        Intent intent = new Intent(HomeActivity.this, NotifActivity.class);
        startActivity(intent);
    }

    public void carrier(View view) {
        Intent i = new Intent(HomeActivity.this, CarrerActivity.class);
        startActivity(i);
    }
}
