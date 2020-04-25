package com.android.projectnew.Activity.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.projectnew.R;
import com.android.projectnew.SessionManager;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private TextView name, email;
    SessionManager sessionManager;
    String getLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getLevel = user.get(sessionManager.LEVEL);

//        String mName = user.get(SessionManager.NAME);
//        String mEmail = user.get(SessionManager.EMAIL);

//        name.setText(mName);
//        email.setText(mEmail);

    }

    public void profile(View view) {
        Intent intent = new Intent(HomeActivity.this, Read_Profile.class);
        startActivity(intent);
        //animation transisi
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void karir(View view) {
        Intent intent = new Intent(HomeActivity.this, CarrerActivity.class);
        startActivity(intent);
        //animation transisi
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void notif(View view) {
        Intent intent = new Intent(HomeActivity.this, NotifActivity.class);
        startActivity(intent);
        //animation transisi
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void carrier(View view) {
        Intent i = new Intent(HomeActivity.this, CarrerActivity.class);
        startActivity(i);
        //animation transisi
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
