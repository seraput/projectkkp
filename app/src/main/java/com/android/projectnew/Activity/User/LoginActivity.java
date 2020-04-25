package com.android.projectnew.Activity.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.projectnew.Activity.Admin.HomeAdmin;
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

import static com.android.projectnew.Api.ApiLocal.URL_LOGIN_USER;


public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btn_login;
    private ProgressBar loading;
    public boolean doubleTapParam = false;
    SessionManager sessionManager;
    SharedPreferences sharedPreferences;
    CheckBox ceklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);


        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        ceklist = findViewById(R.id.saveakun);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();
                
                if (email.getText().toString().length()==0){
                    email.setError("Null Email");
                }
                else if (password.getText().toString().length()==0){
                    password.setError("Null Password");
                }
                else{

                    Login(mEmail, mPass);
                }
            }
        });
        String loginstatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");
        if (loginstatus.equals("LoggedIn")){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
        else if (loginstatus.equals("LoggedOn")){
            startActivity(new Intent(LoginActivity.this, HomeAdmin.class));
        }
    }


    private void Login(final String email, final String password) {
        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String level = object.getString("level").trim();
                                    String id = object.getString("id").trim();

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    if (ceklist.isChecked() && level.equals("user")){
                                        editor.putString(getResources().getString(R.string.prefLoginState),"LoggedIn");
                                    }else if (ceklist.isChecked() && level.equals("admin")){
                                        editor.putString(getResources().getString(R.string.prefLoginState),"LoggedOn");
                                    }
                                    else {
                                        editor.putString(getResources().getString(R.string.prefLoginState),"LoggedOut");
                                    }

                                    if (level.equals("user")){
                                        sessionManager.createSession(name, email, level, id);
                                        editor.apply();
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("email", email);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else if (level.equals("admin")){
                                        sessionManager.createSession(name, email, level, id);
                                        editor.apply();
                                        Intent intent = new Intent(LoginActivity.this, HomeAdmin.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("email", email);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Error, Email Or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Error, Check Connection" +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

//    private void LoginAdmin(final String email, final String password) {
//        loading.setVisibility(View.VISIBLE);
//        btn_login.setVisibility(View.GONE);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_ADMIN,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String success = jsonObject.getString("success");
//                            JSONArray jsonArray = jsonObject.getJSONArray("login");
//
//                            if (success.equals("1")) {
//
//                                for (int i = 0; i < jsonArray.length(); i++) {
//
//                                    JSONObject object = jsonArray.getJSONObject(i);
//
//                                    String name = object.getString("name").trim();
//                                    String email = object.getString("email").trim();
//                                    String level = object.getString("level").trim();
//                                    String id = object.getString("id").trim();
//                                    System.out.println(level);
//
//                                    if (level.equals("user")){
//
//                                        sessionManager.createSession(name, email, id);
//
//                                        Toast.makeText(LoginActivity.this, "User", Toast.LENGTH_SHORT).show();
//
//
//                                        loading.setVisibility(View.GONE);
//
//                                    }
//                                    else if (level.equals("admin")){
//
//                                        Toast.makeText(LoginActivity.this, "Admin", Toast.LENGTH_SHORT).show();
////                                        sessionManager.createSession(name, email, id);
//
////                                        Intent intent = new Intent(LoginActivity.this, HomeAdmin.class);
////                                        intent.putExtra("name", name);
////                                        intent.putExtra("email", email);
////                                        startActivity(intent);
////                                        finish();
//                                    }
////
////                                    Intent intent = new Intent(LoginActivity.this, HomeAdmin.class);
////                                    intent.putExtra("name", name);
////                                    intent.putExtra("email", email);
////                                    startActivity(intent);
////                                    finish();
////
////                                    loading.setVisibility(View.GONE);
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            loading.setVisibility(View.GONE);
//                            btn_login.setVisibility(View.VISIBLE);
//                            Toast.makeText(LoginActivity.this, "Error, Email Or Password", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        loading.setVisibility(View.GONE);
//                        btn_login.setVisibility(View.VISIBLE);
//                        Toast.makeText(LoginActivity.this, "Error, Check Connection" +error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email", email);
//                params.put("password", password);
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }

    public void link_regist(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        //animation transisi
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

//    public void admin(View view) {
//        Intent intent = new Intent(LoginActivity.this, LoginAdminActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//    }
    public void onBackPressed(){
        if (doubleTapParam) {
            super.onBackPressed();
            return;
        }

        this.doubleTapParam = true;
        Toast.makeText(this, "Tap sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleTapParam = false;
            }
        }, 2000);
    }
}
