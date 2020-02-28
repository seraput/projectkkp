package com.android.projectnew.Kuis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectnew.HomeActivity;
import com.android.projectnew.NotifActivity;
import com.android.projectnew.R;
import com.android.projectnew.Read_Profile;
import com.android.projectnew.SessionManager;
import com.android.projectnew.UserActivity;
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

import static com.android.projectnew.Api.ApiLocal.URL_EDIT_NILAI_1;


public class NextKuis2Activity extends AppCompatActivity {

    TextView skorak;
    int score;
    String getId;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_kuis2);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(SessionManager.ID);
        skorak = (TextView) findViewById(R.id.skor);
        Intent intent = getIntent();

        score = intent.getIntExtra("score", 0);
        skorak.setText("" +score);

//        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
//        int highscore = mypref.getInt("highscore", 0);

//        if (highscore>= score){
//            skort.setText(""+highscore);
//        }
//        else{
//            skort.setText(""+score);
//            SharedPreferences.Editor editor= mypref.edit();
//            editor.putInt("highscore", score);
//            editor.commit();
//        }
    }

    private void SaveEditDetail() {

//        final String name = this.name.getText().toString().trim();
//        final String email = this.email.getText().toString().trim();
        final String nilai1 = this.skorak.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT_NILAI_1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("anyText",response);
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(NextKuis2Activity.this, "Success!", Toast.LENGTH_SHORT).show();
//
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(NextKuis2Activity.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(NextKuis2Activity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nilai3", "");
                params.put("nilai2", "");
                params.put("nilai1", nilai1);
                params.put("id", id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void next(View view) {
        SaveEditDetail();
        Intent i = new Intent(NextKuis2Activity.this, ActivityKuis2.class);
        startActivity(i);
    }
}
