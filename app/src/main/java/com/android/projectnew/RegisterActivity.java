package com.android.projectnew;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectnew.ArrayData.ArrayProvKota;
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

import static com.android.projectnew.Api.ApiLocal.URL_REGIST_USER;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, password, c_password, alamat, telepon, validasi;
    private Button btn_regist;
    private ProgressBar loading;
    Spinner Provinsi, Kota;
    RadioButton Pria, Wanita;
    RadioGroup Kelamin;
    private static final String TAG = "RegisterActivity";
    private ArrayProvKota arrayProvKota = new ArrayProvKota();

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
        Provinsi = findViewById(R.id.spProv);
        Kota = findViewById(R.id.spKota);
        Pria = findViewById(R.id.rb_pria);
        Wanita = findViewById(R.id.rb_wanita);
        Kelamin = findViewById(R.id.rg_kelamin);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.provinsi);
        Provinsi.setAdapter(adapter);

        Provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelect = arrayProvKota.provinsi[position];
//                Toast.makeText(MainActivity.this, "Select Item :"+itemSelect, Toast.LENGTH_SHORT).show();
                System.out.println(itemSelect);
                if (position == 0) {
                    ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Provinsi);
                    Kota.setAdapter(adapter0);
                }
                if (position == 1) {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Aceh);
                    Kota.setAdapter(adapter1);
                }
                if (position == 2) {
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Bali);
                    Kota.setAdapter(adapter2);
                }
                if (position == 3) {
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Banten);
                    Kota.setAdapter(adapter3);
                }
                if (position == 4) {
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Bengkulu);
                    Kota.setAdapter(adapter4);
                }
                if (position == 5) {
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Yogyakarta);
                    Kota.setAdapter(adapter5);
                }
                if (position == 6) {
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.DKI_Jakarta);
                    Kota.setAdapter(adapter6);
                }
                if (position == 7) {
                    ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Gorontalo);
                    Kota.setAdapter(adapter7);
                }
                if (position == 8) {
                    ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Jambi);
                    Kota.setAdapter(adapter8);
                }
                if (position == 9) {
                    ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Jawa_Barat);
                    Kota.setAdapter(adapter9);
                }
                if (position == 10) {
                    ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Jawa_Tengah);
                    Kota.setAdapter(adapter10);
                }
                if (position == 11) {
                    ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Jawa_Timur);
                    Kota.setAdapter(adapter11);
                }
                if (position == 12) {
                    ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Kal_Bar);
                    Kota.setAdapter(adapter12);
                }
                if (position == 13) {
                    ArrayAdapter<String> adapter13 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Kal_Sel);
                    Kota.setAdapter(adapter13);
                }
                if (position == 14) {
                    ArrayAdapter<String> adapter14 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Kal_Teng);
                    Kota.setAdapter(adapter14);
                }
                if (position == 15) {
                    ArrayAdapter<String> adapter15 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Kal_Tim);
                    Kota.setAdapter(adapter15);
                }
                if (position == 16) {
                    ArrayAdapter<String> adapter16 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Kal_Utr);
                    Kota.setAdapter(adapter16);
                }
                if (position == 17) {
                    ArrayAdapter<String> adapter17 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Kep_Belitung);
                    Kota.setAdapter(adapter17);
                }
                if (position == 18) {
                    ArrayAdapter<String> adapter18 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Kep_Riau);
                    Kota.setAdapter(adapter18);
                }
                if (position == 19) {
                    ArrayAdapter<String> adapter19 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Lampung);
                    Kota.setAdapter(adapter19);
                }
                if (position == 20) {
                    ArrayAdapter<String> adapter20 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Maluku);
                    Kota.setAdapter(adapter20);
                }
                if (position == 21) {
                    ArrayAdapter<String> adapter21 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Maluku_Utr);
                    Kota.setAdapter(adapter21);
                }
                if (position == 22) {
                    ArrayAdapter<String> adapter22 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Ntb);
                    Kota.setAdapter(adapter22);
                }
                if (position == 23) {
                    ArrayAdapter<String> adapter23 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Ntt);
                    Kota.setAdapter(adapter23);
                }
                if (position == 24) {
                    ArrayAdapter<String> adapter24 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Papua);
                    Kota.setAdapter(adapter24);
                }
                if (position == 25) {
                    ArrayAdapter<String> adapter25 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Papua_Bar);
                    Kota.setAdapter(adapter25);
                }
                if (position == 26) {
                    ArrayAdapter<String> adapter26 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Riau);
                    Kota.setAdapter(adapter26);
                }
                if (position == 27) {
                    ArrayAdapter<String> adapter27 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Sul_Bar);
                    Kota.setAdapter(adapter27);
                }
                if (position == 28) {
                    ArrayAdapter<String> adapter28 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Sul_Sel);
                    Kota.setAdapter(adapter28);
                }
                if (position == 29) {
                    ArrayAdapter<String> adapter29 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Sul_Teng);
                    Kota.setAdapter(adapter29);
                }
                if (position == 30) {
                    ArrayAdapter<String> adapter30 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Sul_Tgr);
                    Kota.setAdapter(adapter30);
                }
                if (position == 31) {
                    ArrayAdapter<String> adapter31 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Sul_Utr);
                    Kota.setAdapter(adapter31);
                }
                if (position == 32) {
                    ArrayAdapter<String> adapter32 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Sum_Bar);
                    Kota.setAdapter(adapter32);
                }
                if (position == 33) {
                    ArrayAdapter<String> adapter33 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Sum_Sel);
                    Kota.setAdapter(adapter33);
                }
                if (position == 34) {
                    ArrayAdapter<String> adapter34 = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayProvKota.Sum_Utr);
                    Kota.setAdapter(adapter34);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kotaValue = Kota.getSelectedItem().toString();
                System.out.println(kotaValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SelectJK = Kelamin.getCheckedRadioButtonId();
                   try {
                       if (SelectJK == Pria.getId() || SelectJK == Wanita.getId()) {
                           Regist();
                       }
                   }catch(Exception e){
                       Toast.makeText(RegisterActivity.this, "Jenis Kelamin Invalid!", Toast.LENGTH_SHORT).show();
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
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + year + "/" + month + "/" + dayOfMonth);

                String date = month + "/" + dayOfMonth + "/" + year;
                tanggal.setText(date);
            }
        };


    }
//
//    public static boolean isValidasiEmail(String email){
//        boolean validasi;
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";
//        String emailPattern2 = "[a-zA-Z-0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
//
//        if (email.matches(emailPattern)){
//            validasi = true;
//        }else if(email.matches(emailPattern2)){
//            validasi = true;
//        }else{
//            validasi = false;
//        }
//        return validasi;
//    }

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

    private void Regist() {
//        loading.setVisibility(View.VISIBLE);
//        btn_regist.setVisibility(View.GONE);

        final String Name = this.name.getText().toString().trim();
        final String Email = this.email.getText().toString().trim();
        final String Tanggal = this.tanggal.getText().toString().trim();
        final String Alamat = this.alamat.getText().toString().trim();
        final String Telepon = this.telepon.getText().toString().trim();
        final String Pass = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            if (name.getText().toString().length() == 0) {
                                name.setError("Name Null!");
                            } else if (email.getText().toString().length() == 0) {
                                email.setError("Email Null!");
                            } else if (alamat.getText().toString().length() == 0) {
                                alamat.setError("Address Null!");
                            } else if (telepon.getText().toString().length() == 0) {
                                telepon.setError("Telephone Null!");
                            } else if (password.getText().toString().length() <= 7) {
                                password.setError("Password Min 8");
                            }else {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                if (success.equals("1")) {
                                    Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    btn_regist.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
//                            loading.setVisibility(View.GONE);
//                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Error!" + error, Toast.LENGTH_SHORT).show();
//                        loading.setVisibility(View.GONE);
//                        btn_regist.setVisibility(View.VISIBLE);
                    }
                }) {
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

//    private void validasi() {
//        if (name.getText().toString().length() == 0) {
//            name.setError("Name Null!");
//        } else if (email.getText().toString().length() == 0) {
//            email.setError("Email Null!");
//        } else if (alamat.getText().toString().length() == 0) {
//            alamat.setError("Address Null!");
//        } else if (telepon.getText().toString().length() == 0) {
//            telepon.setError("Telephone Null!");
//        } else if (password.getText().toString().length() <= 7) {
//            password.setError("Password Min 8");
//        }
//    }
}
