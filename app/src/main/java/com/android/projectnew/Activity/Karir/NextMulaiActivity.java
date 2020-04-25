package com.android.projectnew.Activity.Karir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.projectnew.Activity.User.HomeActivity;
import com.android.projectnew.Activity.Kuis.ActivityKuis1;
import com.android.projectnew.Activity.User.NotifActivity;
import com.android.projectnew.R;
import com.android.projectnew.Activity.User.UserActivity;

public class NextMulaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_mulai);
    }

    public void mulai(View view) {
        Intent i = new Intent(NextMulaiActivity.this, ActivityKuis1.class);
        startActivity(i);
    }

    public void home(View view) {
        Intent i = new Intent(NextMulaiActivity.this, HomeActivity.class);
        startActivity(i);
    }

    public void notif(View view) {
        Intent i = new Intent(NextMulaiActivity.this, NotifActivity.class);
        startActivity(i);
    }

    public void profil(View view) {
        Intent i = new Intent(NextMulaiActivity.this, UserActivity.class);
        startActivity(i);
    }
}
