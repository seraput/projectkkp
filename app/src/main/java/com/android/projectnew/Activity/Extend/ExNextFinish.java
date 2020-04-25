package com.android.projectnew.Activity.Extend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectnew.Activity.User.HomeActivity;
import com.android.projectnew.Activity.Kuis.NextFinishActivity;
import com.android.projectnew.R;
import com.android.projectnew.SessionManager;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.android.projectnew.Api.ApiLocal.URL_CREATE_STATUS;
import static com.android.projectnew.Api.ApiLocal.URL_READ_DETAIL;

public class ExNextFinish extends AppCompatActivity {

    private static final String TAG = NextFinishActivity.class.getSimpleName();
    private TextView name, email, tgl, status;
    int hari, bulan, tahun;
    TextView id, nilai1, nilai2, nilai_3;
    String getId;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_next_finish);

        sessionManager = new SessionManager(this);

        Calendar calendar = Calendar.getInstance();
        hari = calendar.get(Calendar.DAY_OF_MONTH);
        bulan = calendar.get(Calendar.MONTH);
        tahun = calendar.get(Calendar.YEAR);

        bulan = bulan + 1;
        String finalTanggal = bulan + "/" + hari + "/" + tahun;

        tgl = findViewById(R.id.tanggal);
        tgl.setText(finalTanggal);

        id = findViewById(R.id.iduser);
        name = findViewById(R.id.nama);
        email = findViewById(R.id.mail);
        nilai1 = findViewById(R.id.nilai1);
        nilai2 = findViewById(R.id.nilai2);
        nilai_3 = findViewById(R.id.nilai_3);
        status = findViewById(R.id.status);
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

    }

    private void getUserDetail(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ_DETAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){

                                for (int i = 0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strId = object.getString("id").trim();
                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strNilai1 = object.getString("nilai1").trim();
                                    String strNilai2 = object.getString("nilai2").trim();
                                    String strNilai3 = object.getString("nilai3").trim();


                                    id.setText(strId);
                                    name.setText(strName);
                                    email.setText(strEmail);
                                    nilai1.setText(strNilai1);
                                    nilai2.setText(strNilai2);
                                    nilai_3.setText(strNilai3);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ExNextFinish.this, "Gagal Memuat Data "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ExNextFinish.this, "Gagal Memuat Data "+error.toString(), Toast.LENGTH_SHORT).show();
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

    public void next(View view) {
        final String id = this.id.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String name = this.name.getText().toString().trim();
        final String nilai1 = this.nilai1.getText().toString().trim();
        final String nilai2 = this.nilai2.getText().toString().trim();
        final String nilai_3 = this.nilai_3.getText().toString().trim();
        final String tgl = this.tgl.getText().toString().trim();
        final String status = this.status.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CREATE_STATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(ExNextFinish.this, "Success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ExNextFinish.this, HomeActivity.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ExNextFinish.this, "Error!" +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ExNextFinish.this, "Error!" +error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("email", email);
                params.put("nama", name);
                params.put("nilai1", nilai1);
                params.put("nilai2", nilai2);
                params.put("nilai3", nilai_3);
                params.put("tanggal", tgl);
                params.put("status", status);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
