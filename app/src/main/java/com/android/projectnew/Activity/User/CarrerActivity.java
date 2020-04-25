package com.android.projectnew.Activity.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.projectnew.Activity.Karir.NextKarirActivity;
import com.android.projectnew.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class CarrerActivity extends AppCompatActivity {
//    private SlidrInterface slidr;
//    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrer);
//        slidr = Slidr.attach(this);
//        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

//        viewFlipper.showNext();
    }

    public void home(View view) {
        Intent i = new Intent(CarrerActivity.this, HomeActivity.class);
        startActivity(i);
        //animation transisi
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void karir(View view) {
        Intent i = new Intent(CarrerActivity.this, CarrerActivity.class);
        startActivity(i);
        //animation transisi
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void notif(View view) {
        Intent i = new Intent(CarrerActivity.this, NotifActivity.class);
        startActivity(i);
        //animation transisi
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    public void loker1(View view) {
        Intent i = new Intent(CarrerActivity.this, NextKarirActivity.class);
        startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void loker2(View view) {
        Intent i = new Intent(CarrerActivity.this, NextKarirActivity.class);
        startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void loker3(View view) {
        Intent i = new Intent(CarrerActivity.this, NextKarirActivity.class);
        startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void profil(View view) {
        Intent i = new Intent(CarrerActivity.this, Read_Profile.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
