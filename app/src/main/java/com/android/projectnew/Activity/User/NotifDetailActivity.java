package com.android.projectnew.Activity.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
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
import java.util.HashMap;
import java.util.Map;
import static com.android.projectnew.Api.ApiLocal.URL_KETERANGAN;

public class NotifDetailActivity extends AppCompatActivity {

    TextView tvId, tvEmail, tvNama, tvIsi, tvNilai2, tvNilai3, tvTanggal, tvStatus;
    int position;
    String getID;
    SessionManager sessionManager;
    public static String URL_SETKETERANGAN = "http://192.168.0.117/api/kkp_project/user/getKeterangan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_detail);

        tvEmail = findViewById(R.id.email_id);
        tvNama = findViewById(R.id.nama_id);
        tvTanggal = findViewById(R.id.tanggal_id);
        tvStatus = findViewById(R.id.status_id);
        tvIsi = findViewById(R.id.isi);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getID = user.get(SessionManager.ID);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

//        tvId.setText(NotifActivity.dataItemArrayListUser.get(position).getId());
        tvEmail.setText(NotifActivity.dataItemArrayListUser.get(position).getEmail());
        tvNama.setText(NotifActivity.dataItemArrayListUser.get(position).getNama());
//        tvNilai1.setText(NotifActivity.dataItemArrayListUser.get(position).getNilai1());
//        tvNilai2.setText(NotifActivity.dataItemArrayListUser.get(position).getNilai2());
//        tvNilai3.setText(NotifActivity.dataItemArrayListUser.get(position).getNilai3());
        tvTanggal.setText(NotifActivity.dataItemArrayListUser.get(position).getTanggal());
        tvStatus.setText(NotifActivity.dataItemArrayListUser.get(position).getStatus());

        receiveData();
    }

    public void receiveData(){
        StringRequest request = new StringRequest(Request.Method.POST, URL_SETKETERANGAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (sucess.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strKet = object.getString("keterangan").trim();

                                    tvIsi.setText(strKet);
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
                        Toast.makeText(NotifDetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}
