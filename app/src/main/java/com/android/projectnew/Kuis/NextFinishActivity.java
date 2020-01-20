package com.android.projectnew.Kuis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectnew.Extend.ExNextFinish;
import com.android.projectnew.Extend.ExNextKuis3;
import com.android.projectnew.HomeActivity;
import com.android.projectnew.LoginActivity;
import com.android.projectnew.R;
import com.android.projectnew.Read_Profile;
import com.android.projectnew.RegisterActivity;
import com.android.projectnew.SessionManager;
import com.android.projectnew.UserActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NextFinishActivity extends AppCompatActivity {

    private static final String TAG = NextFinishActivity.class.getSimpleName();
    private TextView name, email;
    TextView skorak;
    private Button btn_selesai;
    private ProgressBar loading;
    int score;
    String getId;
    Integer nilai3;
    SessionManager sessionManager;

    private static String URL_EDIT ="http://192.168.43.25/api/kkp_project/update_nilai3.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_finish);

        sessionManager = new SessionManager(this);

        name = findViewById(R.id.nama);
        email = findViewById(R.id.mail);
//
//        loading = findViewById(R.id.loading);
//        btn_selesai = findViewById(R.id.btn_mulai);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);
//        getEmail = user.get(SessionManager.EMAIL);
//        getNama = user.get(SessionManager.NAME);
//        getNilai = user.get(SessionManager.NILAI);
//        getNilai2 = user.get(SessionManager.NILAI2);
//        getNilai3 = user.get(SessionManager.NILAI3);

        skorak = (TextView) findViewById(R.id.skor);
        Intent intent = getIntent();

        score = intent.getIntExtra("score", 0);
        skorak.setText("" +score);
    }


    private void SaveEditDetail() {
//
//        final String name = this.name.getText().toString().trim();
//        final String email = this.email.getText().toString().trim();
        final String nilai3 = this.skorak.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("anyText",response);
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(NextFinishActivity.this, "Success!", Toast.LENGTH_SHORT).show();
//
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(NextFinishActivity.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(NextFinishActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nilai3", nilai3);
                params.put("id", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void next(View view) {
        SaveEditDetail();
        Intent intent = new Intent(NextFinishActivity.this, ExNextFinish.class);
        startActivity(intent);
    }
}
