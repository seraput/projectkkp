package com.android.projectnew.Activity.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import de.hdodenhof.circleimageview.CircleImageView;
import static com.android.projectnew.Api.ApiLocal.URL_READ;

public class Read_Profile extends AppCompatActivity {

    private static final String TAG = Read_Profile.class.getSimpleName();
    private TextView name, email, tanggal, alamat, telepon;
    SessionManager sessionManager;
    private Button logout;
    String getId;
    private Bitmap bitmap;
    CircleImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__profile);

        sessionManager = new SessionManager(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        tanggal = findViewById(R.id.tanggal);
        alamat = findViewById(R.id.alamat);
        telepon = findViewById(R.id.telepon);
        logout = findViewById(R.id.logout);
        profile_image = findViewById(R.id.profile_image);

        HashMap<String, String> user = sessionManager.getUserDetail();
        final SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        getId = user.get(SessionManager.ID);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.prefLoginState),"LoggedOut");
                editor.apply();
                startActivity(new Intent(Read_Profile.this, LoginActivity.class));
                finish();
            }
        });
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
                                Toast.makeText(Read_Profile.this, "Gagal Memuat Data "+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(Read_Profile.this, "Gagal Memuat Data "+error.toString(), Toast.LENGTH_SHORT).show();
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

    public void edit(View view) {
        Intent i = new Intent(Read_Profile.this, UserActivity.class);
        startActivity(i);
        //animation transisi
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void home(View view) {
        Intent i = new Intent(Read_Profile.this, HomeActivity.class);
        startActivity(i);
        //animation transisi
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void karir(View view) {
        Intent i = new Intent(Read_Profile.this, CarrerActivity.class);
        startActivity(i);
        //animation transisi
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void notif(View view) {
        Intent i = new Intent(Read_Profile.this, NotifActivity.class);
        startActivity(i);
        //animation transisi
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
