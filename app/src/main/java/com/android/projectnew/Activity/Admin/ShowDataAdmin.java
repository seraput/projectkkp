package com.android.projectnew.Activity.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.projectnew.Activity.User.NotifActivity;
import com.android.projectnew.Adapter.AdapterDataAdmin;
import com.android.projectnew.Adapter.AdapterNotif;
import com.android.projectnew.DataController.NotifUser.DataItemAdmin;
import com.android.projectnew.DataController.NotifUser.DataItemUser;
import com.android.projectnew.R;
import com.android.projectnew.SessionManager;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShowDataAdmin extends AppCompatActivity {

    SessionManager sessionManager;
    String getId;
    ListView listView;
    AdapterDataAdmin adapterDataAdmin;
    public static ArrayList<DataItemAdmin> dataItemAdminArrayList = new ArrayList<>();
    String URLRead="http://192.168.0.117/api/kkp_project/admin/show_all.php";
    DataItemAdmin dataItemAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_admin);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();

        listView = findViewById(R.id.myListviewAdmData);
        adapterDataAdmin = new AdapterDataAdmin(this, dataItemAdminArrayList);
        listView.setAdapter(adapterDataAdmin);

        receiveData();
    }

    public void receiveData(){
        StringRequest request = new StringRequest(Request.Method.POST, URLRead,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dataItemAdminArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (sucess.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String email = object.getString("email");
                                    String name = object.getString("name");
                                    String telepon = object.getString("telepon");
                                    String level = object.getString("level");

                                    dataItemAdmin = new DataItemAdmin(id, email, name, telepon, level);
                                    dataItemAdminArrayList.add(dataItemAdmin);
                                    adapterDataAdmin.notifyDataSetChanged();
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
                        Toast.makeText(ShowDataAdmin.this, error.toString(), Toast.LENGTH_SHORT).show();
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
}
