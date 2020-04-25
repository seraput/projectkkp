package com.android.projectnew.Activity.Admin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.projectnew.Activity.User.NotifActivity;
import com.android.projectnew.Activity.User.NotifDetailActivity;
import com.android.projectnew.Activity.User.Read_Profile;
import com.android.projectnew.Adapter.AdapterNotif;
import com.android.projectnew.DataController.NotifUser.DataItemUser;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotifAdmin extends AppCompatActivity {
    SessionManager sessionManager;
    String getId;
    ListView listViewAdm;
    AdapterNotif adapterNotif;
    public static ArrayList<DataItemUser> dataItemArrayListUser = new ArrayList<>();
    String URLRead="http://192.168.0.117/api/kkp_project/admin/show_all.php";
    DataItemUser dataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_admin);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);

        listViewAdm = findViewById(R.id.myListviewAdm);
        adapterNotif = new AdapterNotif(this, dataItemArrayListUser);
        listViewAdm.setAdapter(adapterNotif);

        listViewAdm.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                startActivity(new Intent(getApplicationContext(), DetailNotifAdmin.class)
                        .putExtra("position", position));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

        receiveData();
    }

    public void receiveData(){
        StringRequest request = new StringRequest(Request.Method.POST, URLRead,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dataItemArrayListUser.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (sucess.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String email = object.getString("email");
                                    String nama = object.getString("nama");
                                    String nilai1 = object.getString("nilai1");
                                    String nilai2 = object.getString("nilai2");
                                    String nilai3 = object.getString("nilai3");
                                    String tanggal = object.getString("tanggal");
                                    String status = object.getString("status");

                                    dataItem = new DataItemUser(id, email, nama, nilai1, nilai2, nilai3, tanggal, status);
                                    dataItemArrayListUser.add(dataItem);
                                    adapterNotif.notifyDataSetChanged();
                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NotifAdmin.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        requestQueue.add(request);
    }

    public void prof(View view) {
        Intent i = new Intent(NotifAdmin.this, AdminProfile.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void home(View view) {
        Intent i = new Intent(NotifAdmin.this, HomeAdmin.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
