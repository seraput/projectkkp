package com.android.projectnew.Activity.Extend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.projectnew.Activity.Kuis.ActivityKuis3;
import com.android.projectnew.R;

public class ExNextKuis3Mulai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_next_kuis3_mulai);
    }

    public void next(View view) {
        Intent i = new Intent(ExNextKuis3Mulai.this, ActivityKuis3.class);
        startActivity(i);
    }
}
