package com.android.projectnew.Activity.User;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.android.projectnew.Api.ApiLocal.URL_READ_DATA;


public class NotifActivity extends AppCompatActivity {

    private static final String TAG = NotifActivity.class.getSimpleName();
    SessionManager sessionManager;
    String getId;
    ListView listView;
    AdapterNotif adapterNotif;
    public static ArrayList<DataItemUser> dataItemArrayListUser = new ArrayList<>();
    String URLRead="https://allprojectdb.000webhostapp.com/user/show.php";
    DataItemUser dataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);

        listView = findViewById(R.id.myListview);
        adapterNotif = new AdapterNotif(this, dataItemArrayListUser);
        listView.setAdapter(adapterNotif);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                startActivity(new Intent(getApplicationContext(),NotifDetailActivity.class)
                        .putExtra("position", position));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

//                CharSequence[] dialogItem = {"View Data"};
//                builder.setTitle(dataItemArrayList.get(position).getEmail());
//                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        switch (i) {
//                            case 0:
//                                startActivity(new Intent(getApplicationContext(),DetailData.class)
//                                        .putExtra("position", position));
//                                break;
//                            case 1:
//                                break;
//                            case 2:
//                                break;
//                        }
//                    }
//                });
//                builder.create().show();
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
                        Toast.makeText(NotifActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void home(View view) {
        Intent i = new Intent(NotifActivity.this, HomeActivity.class);
        startActivity(i);
        //animation transisi
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void karir(View view) {
        Intent i = new Intent(NotifActivity.this, CarrerActivity.class);
        startActivity(i);
        //animation transisi
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void profil(View view) {
        Intent i = new Intent(NotifActivity.this, Read_Profile.class);
        startActivity(i);
        //animation transisi
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
