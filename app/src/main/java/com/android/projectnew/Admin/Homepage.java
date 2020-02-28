package com.android.projectnew.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.projectnew.R;
import com.android.projectnew.SessionManager;
import com.android.projectnew.SessionManagerAdmin;

import java.util.HashMap;

public class Homepage extends AppCompatActivity {


    private TextView name, email;
    SessionManagerAdmin sessionManagerAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        sessionManagerAdmin = new SessionManagerAdmin(this);
        sessionManagerAdmin.checkLogin();
        HashMap<String, String> user = sessionManagerAdmin.getUserDetail();
    }
}
