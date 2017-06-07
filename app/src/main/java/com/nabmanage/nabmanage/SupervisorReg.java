package com.nabmanage.nabmanage;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;


public class SupervisorReg extends AppCompatActivity implements View.OnClickListener {

    private EditText etName,etEmail,etPassword,etPhone;
    private Button bSvReg;
    private ProgressDialog progressDialog;
    private Spinner locSpinner;

    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_reg);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, SvProfile.class));
            return;
        }

        etName = (EditText) findViewById(R.id.et_s_name);
        etEmail = (EditText) findViewById(R.id.et_s_email);
        etPassword = (EditText) findViewById(R.id.et_s_password);
        etPhone = (EditText) findViewById(R.id.et_s_phone);
        bSvReg = (Button) findViewById(R.id.b_s_register);
        //etLocation = (EditText) findViewById(R.id.et_s_location);
        locSpinner = (Spinner) findViewById(R.id.sp_s_location);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SupervisorReg.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locations));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locSpinner.setAdapter(myAdapter);


        tvLogin = (TextView) findViewById(R.id.tvSvLogin);

        progressDialog = new ProgressDialog(this);

        bSvReg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    private void registerSv(){
        final String email = etEmail.getText().toString().trim();
        final String name = etName.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String phone = etPhone.getText().toString().trim();
        //final String location = etLocation.getText().toString().trim();
        final String location = locSpinner.getSelectedItem().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", name);
                params.put("email", email);
                params.put("password", password);
                params.put("location", location);
                params.put("phone", phone);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == bSvReg)
            registerSv();
        if(v == tvLogin)
            startActivity(new Intent(this, Login.class));
    }
}