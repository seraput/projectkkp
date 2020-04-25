package com.android.projectnew.Activity.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectnew.Activity.Extend.ExNextFinish;
import com.android.projectnew.Activity.User.HomeActivity;
import com.android.projectnew.Activity.User.NotifActivity;
import com.android.projectnew.Activity.User.UserActivity;
import com.android.projectnew.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.projectnew.Api.ApiLocal.URL_CREATE_Keterangan;
import static com.android.projectnew.Api.ApiLocal.URL_CREATE_STATUS;
import static com.android.projectnew.Api.ApiLocal.URL_EDIT;
import static com.android.projectnew.Api.ApiLocal.URL_UPDATE_STATUS;

public class DetailNotifAdmin extends AppCompatActivity {

    TextView tvId, tvEmail, tvNama, tvNilai1, tvNilai2, tvNilai3, tvTanggal, tvStatus, tidak, lolos, ketLolos, KetTidak;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notif_admin);

        tvEmail = findViewById(R.id.email_id_adm);
        tvNama = findViewById(R.id.nama_id_adm);
        tvTanggal = findViewById(R.id.tanggal_id_adm);
        tvStatus = findViewById(R.id.status_id_adm);
        tvNilai1 = findViewById(R.id.nilai1_id_adm);
        tvNilai2 = findViewById(R.id.nilai2_id_adm);
        tvNilai3 = findViewById(R.id.nilai3_id_adm);
        tvId = findViewById(R.id.id_id_adm);
        lolos = findViewById(R.id.tvLolos);
        tidak = findViewById(R.id.tvGagal);
        ketLolos = findViewById(R.id.ket_lolos);
        KetTidak = findViewById(R.id.ket_tidak);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        tvId.setText(NotifAdmin.dataItemArrayListUser.get(position).getId());
        tvEmail.setText(NotifAdmin.dataItemArrayListUser.get(position).getEmail());
        tvNama.setText(NotifAdmin.dataItemArrayListUser.get(position).getNama());
        tvNilai1.setText(NotifAdmin.dataItemArrayListUser.get(position).getNilai1());
        tvNilai2.setText(NotifAdmin.dataItemArrayListUser.get(position).getNilai2());
        tvNilai3.setText(NotifAdmin.dataItemArrayListUser.get(position).getNilai3());
        tvTanggal.setText(NotifAdmin.dataItemArrayListUser.get(position).getTanggal());
        tvStatus.setText(NotifAdmin.dataItemArrayListUser.get(position).getStatus());

    }

    public void Lolos(View view) {
        String id = tvId.getText().toString();
        String keterangan = ketLolos.getText().toString();
        Intent intent = new Intent(DetailNotifAdmin.this, HomeAdmin.class);
        startActivity(intent);
        UpdateStatusLolos();
        keteranganLolos(id, keterangan);
    }

    private void keteranganLolos(final String id, final String keterangan) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CREATE_Keterangan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(DetailNotifAdmin.this, "Success!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DetailNotifAdmin.this, "Error!" +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailNotifAdmin.this, "Error!" +error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("keterangan", keterangan);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void Tidak(View view) {
        String id = tvId.getText().toString();
        String keterangan = KetTidak.getText().toString();
        Intent intent = new Intent(DetailNotifAdmin.this, AdminProfile.class);
        startActivity(intent);
        UpdateStatusGagal();
        keteranganTidak(id, keterangan);
    }

    private void keteranganTidak(final String id, final String keterangan) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CREATE_Keterangan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(DetailNotifAdmin.this, "Success!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DetailNotifAdmin.this, "Error!" +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailNotifAdmin.this, "Error!" +error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("keterangan", keterangan);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void UpdateStatusLolos() {

        final String status = this.lolos.getText().toString().trim();
        final String id = this.tvId.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_STATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(DetailNotifAdmin.this, "Success!", Toast.LENGTH_SHORT).show();
//                                sessionManager.createSession(email, name, id);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(DetailNotifAdmin.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(DetailNotifAdmin.this, "Connection Error", Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("status", status);
                params.put("id", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
//
    private void UpdateStatusGagal() {

        final String status = this.tidak.getText().toString().trim();
        final String id = this.tvId.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_STATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(DetailNotifAdmin.this, "Success!", Toast.LENGTH_SHORT).show();
//                                sessionManager.createSession(email, name, id);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(DetailNotifAdmin.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(DetailNotifAdmin.this, "Connection Error", Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("status", status);
                params.put("id", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
