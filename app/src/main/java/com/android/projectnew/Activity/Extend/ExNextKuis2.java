package com.android.projectnew.Activity.Extend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectnew.Activity.User.HomeActivity;
import com.android.projectnew.Activity.User.NotifActivity;
import com.android.projectnew.R;
import com.android.projectnew.Activity.User.Read_Profile;
import com.android.projectnew.SessionManager;
import com.android.projectnew.Activity.User.UserActivity;
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

import static com.android.projectnew.Api.ApiLocal.URL_READ_DETAIL;


public class ExNextKuis2 extends AppCompatActivity {

    private static final String TAG = UserActivity.class.getSimpleName();
    private TextView name, email, tanggal, telepon, alamat;
    SessionManager sessionManager;
    String getId;
    Integer nilai1, nilai2, nilai3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_next_kuis2);

        sessionManager = new SessionManager(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        tanggal = findViewById(R.id.tanggal);
        telepon = findViewById(R.id.telepon);
        alamat = findViewById(R.id.alamat);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);
    }

    private void getUserDetail() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ_DETAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i("tes", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strTanggal = object.getString("tanggal").trim();
                                    String strTelepon = object.getString("telepon").trim();
                                    String strAlamat = object.getString("alamat").trim();
                                    nilai1 = object.getInt("nilai1");
                                    nilai2 = object.getInt("nilai2");
                                    nilai3 = object.getInt("nilai3");

//                                    name.setText(strName);
//                                    email.setText(strEmail);
//                                    tanggal.setText(strTanggal);
//                                    telepon.setText(strTelepon);
//                                    alamat.setText(strAlamat);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ExNextKuis2.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ExNextKuis2.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    };

    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

    public void profil(View view) {
        Intent intent = new Intent(ExNextKuis2.this, Read_Profile.class);
        startActivity(intent);
    }

    public void notif(View view) {
        Intent intent = new Intent(ExNextKuis2.this, NotifActivity.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(ExNextKuis2.this, HomeActivity.class);
        startActivity(intent);
    }

    public void lanjut(View view) {
        System.out.println(nilai2);
        if(nilai2 == 0){
            Intent ip = new Intent(ExNextKuis2.this, ExNextKuis2Mulai.class);
            startActivity(ip);
        }else if (nilai2 > 0){
            Toast.makeText(this, "Nilai Psikotes Ke-2 Sudah Terisi.", Toast.LENGTH_SHORT).show();
            Intent ip = new Intent(ExNextKuis2.this, ExNextKuis3.class);
            startActivity(ip);
        }
    }
}
