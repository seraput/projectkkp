package com.android.projectnew.Activity.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.projectnew.Activity.User.LoginActivity;
import com.android.projectnew.R;
import com.android.projectnew.SessionManager;
import java.util.HashMap;

public class HomeAdmin extends AppCompatActivity {


    private TextView name, email;
    SessionManager sessionManager;
    String getLevel;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        sessionManager = new SessionManager(this);
        final SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getLevel = user.get(SessionManager.LEVEL);
        logout = findViewById(R.id.logoutAA);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.prefLoginState),"LoggedOut");
                editor.apply();
                startActivity(new Intent(HomeAdmin.this, LoginActivity.class));
                finish();
            }
        });

    }

    public void prof(View view) {
        Intent i = new Intent(HomeAdmin.this, AdminProfile.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    public void notif(View view) {
        startActivity(new Intent(HomeAdmin.this, NotifAdmin.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void addMember(View view) {
        Intent i = new Intent(HomeAdmin.this, TambahAdmin.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void showMember(View view) {
        Intent i = new Intent(HomeAdmin.this, ShowDataAdmin.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
