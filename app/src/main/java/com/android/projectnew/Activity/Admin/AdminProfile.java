package com.android.projectnew.Activity.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectnew.Activity.User.LoginActivity;
import com.android.projectnew.R;
import com.android.projectnew.Activity.User.Read_Profile;
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

import java.util.HashMap;
import java.util.Map;

import static com.android.projectnew.Api.ApiLocal.URL_READ;

public class AdminProfile extends AppCompatActivity {

    private static final String TAG = AdminProfile.class.getSimpleName();
    private TextView name, email, tanggal, alamat, telepon;
    SessionManager sessionManager;
    private Button logout;
    String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        sessionManager = new SessionManager(this);
        final SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);

        name = findViewById(R.id.nameAA);
        email = findViewById(R.id.emailAA);
        tanggal = findViewById(R.id.tanggalAA);
        alamat = findViewById(R.id.alamatAA);
        telepon = findViewById(R.id.teleponAA);
        logout = findViewById(R.id.logoutAA);

    }

    private void getUserDetail(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
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

                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strTanggal = object.getString("tanggal").trim();
                                    String strAlamat = object.getString("alamat").trim();
                                    String strTelepon = object.getString("telepon").trim();


                                    name.setText(strName);
                                    email.setText(strEmail);
                                    tanggal.setText(strTanggal);
                                    alamat.setText(strAlamat);
                                    telepon.setText(strTelepon);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(AdminProfile.this, "Gagal Memuat Data "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(AdminProfile.this, "Gagal Memuat Data "+error.toString(), Toast.LENGTH_SHORT).show();
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

    public void notif(View view) {
        startActivity(new Intent(AdminProfile.this, NotifAdmin.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void Home(View view) {
        startActivity(new Intent(AdminProfile.this, HomeAdmin.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
