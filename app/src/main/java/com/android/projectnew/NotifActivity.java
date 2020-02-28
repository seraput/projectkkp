package com.android.projectnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.android.projectnew.Api.ApiLocal.URL_READ_DATA;


public class NotifActivity extends AppCompatActivity {

    private static final String TAG = NotifActivity.class.getSimpleName();
    private TextView id, nama, email, tgl, status, nilai1, nilai2, nilai3;
    SessionManager sessionManager;
    String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        sessionManager = new SessionManager(this);

        id = findViewById(R.id.iduser);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        tgl = findViewById(R.id.tanggal);
        status = findViewById(R.id.status);
        nilai1 = findViewById(R.id.nilai1);
        nilai2 = findViewById(R.id.nilai2);
        nilai3 = findViewById(R.id.nilai3);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);
    }

    private void getUserDetail(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e(TAG, response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){

                                for (int i = 0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strId = object.getString("id").trim();
                                    String strName = object.getString("nama").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strNilai1 = object.getString("nilai1").trim();
                                    String strNilai2 = object.getString("nilai2").trim();
                                    String strNilai3 = object.getString("nilai3").trim();
                                    String strTanggal = object.getString("tanggal").trim();
                                    String strStatus = object.getString("status").trim();


                                    id.setText(strId);
                                    nama.setText(strName);
                                    email.setText(strEmail);
                                    nilai1.setText(strNilai1);
                                    nilai2.setText(strNilai2);
                                    nilai3.setText(strNilai3);
                                    tgl.setText(strTanggal);
                                    status.setText(strStatus);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(NotifActivity.this, "Gagal Memuat Data "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(NotifActivity.this, "Gagal Memuat Data "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

    public void home(View view) {
        Intent i = new Intent(NotifActivity.this, HomeActivity.class);
        startActivity(i);
    }

    public void karir(View view) {
        Intent i = new Intent(NotifActivity.this, CarrerActivity.class);
        startActivity(i);
    }

    public void profil(View view) {
        Intent i = new Intent(NotifActivity.this, Read_Profile.class);
        startActivity(i);
    }
}
