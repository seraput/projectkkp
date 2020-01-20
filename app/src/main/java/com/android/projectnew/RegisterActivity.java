package com.android.projectnew;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, password, c_password, alamat, telepon;
    private Button btn_regist;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.0.110/api/kkp_project/register.php";
    private static final String TAG = "RegisterActivity";

    private TextView tanggal;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Created Account");
        setContentView(R.layout.register_activity_main);

        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_regist = findViewById(R.id.btn_regist);
        tanggal = findViewById(R.id.tvDate);
        alamat = findViewById(R.id.alamat);
        telepon = findViewById(R.id.telepon);

//        name.addTextChangedListener(RegistTW);
//        email.addTextChangedListener(RegistTW);
//        alamat.addTextChangedListener(RegistTW);
//        telepon.addTextChangedListener(RegistTW);
//        password.addTextChangedListener(RegistTW);
//        c_password.addTextChangedListener(RegistTW);


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = name.getText().toString().trim();
                String mEmail = email.getText().toString().trim();
                String mAlamat = alamat.getText().toString().trim();
                String mTelepon = telepon.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mName.isEmpty() && !mEmail.isEmpty() && !mAlamat.isEmpty() && !mTelepon.isEmpty() && !mPass.isEmpty()){
                    Regist();
                }
//                else if (password != c_password){
//                    Toast.makeText(RegisterActivity.this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
//                }
                else{
                    Toast.makeText(RegisterActivity.this, "Pastikan Semua Terisi", Toast.LENGTH_SHORT).show();
                    name.setError("Nama Lengkap");
                    email.setError("Email");
                    alamat.setError("Alamat");
                    telepon.setError("Nomer Telepon");
                    password.setError("Password");
                }
            }
        });



        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: "+ year +"/"+ month +"/"+ dayOfMonth);

                String date = month + "/" + dayOfMonth + "/" + year;
                tanggal.setText(date);
            }
        };
    }

//    private TextWatcher RegistTW = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String nameinput = name.getText().toString().trim();
//            String emailinput = email.getText().toString().trim();
//            String alamatinput = alamat.getText().toString().trim();
//            String teleponinput = telepon.getText().toString().trim();
//            String passinput = password.getText().toString().trim();
//            String cpassinput = c_password.getText().toString().trim();
//
//            btn_regist.setEnabled(!nameinput.isEmpty() && !emailinput.isEmpty() && !alamatinput.isEmpty()
//                    && !teleponinput.isEmpty() && !passinput.isEmpty() && !cpassinput.isEmpty());
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String Name = this.name.getText().toString().trim();
        final String Email = this.email.getText().toString().trim();
        final String Tanggal = this.tanggal.getText().toString().trim();
        final String Alamat = this.alamat.getText().toString().trim();
        final String Telepon = this.telepon.getText().toString().trim();
        final String Pass = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error!" +e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Error!" +error, Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", Name);
                params.put("email", Email);
                params.put("tanggal", Tanggal);
                params.put("alamat", Alamat);
                params.put("telepon", Telepon);
                params.put("password", Pass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
