package com.android.projectnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.android.projectnew.Karir.NextKarirActivity;

public class CarrerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrer);
    }

    public void home(View view) {
        Intent i = new Intent(CarrerActivity.this, HomeActivity.class);
        startActivity(i);
    }

    public void karir(View view) {
        Intent i = new Intent(CarrerActivity.this, CarrerActivity.class);
        startActivity(i);
    }

    public void notif(View view) {
        Intent i = new Intent(CarrerActivity.this, NotifActivity.class);
        startActivity(i);
    }


    public void loker1(View view) {
        Intent i = new Intent(CarrerActivity.this, NextKarirActivity.class);
        startActivity(i);
    }

    public void loker2(View view) {
        Intent i = new Intent(CarrerActivity.this, NextKarirActivity.class);
        startActivity(i);
    }

    public void loker3(View view) {
        Intent i = new Intent(CarrerActivity.this, NextKarirActivity.class);
        startActivity(i);
    }

    public void profil(View view) {
        Intent i = new Intent(CarrerActivity.this, UserActivity.class);
        startActivity(i);
    }
}
