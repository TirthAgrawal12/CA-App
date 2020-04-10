package com.example.dell.siapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText mEmailEt, mPasswordEt;
    Spinner mformType;
    Button mLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailEt = findViewById(R.id.emailEt);
        mPasswordEt = findViewById(R.id.passwordEt);
        mLoginButton = findViewById(R.id.Loginbtn);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login();
            }
        });
        //To show list of forms
        setFormList();
    }

    private void Login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://localhost/si/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")){
                            String choice = mformType.getSelectedItem().toString();
                            if (choice.equals("form1")) {
                                Intent intent = new Intent(MainActivity.this, ITR_form1.class);
                                intent.putExtra("email",mEmailEt.getText().toString());
                                startActivity(intent);
                            } else if (choice.equals("form2")) {
                                startActivity(new Intent(MainActivity.this, ITR_form2.class));
                            } else if (choice.equals("form3")) {
                                startActivity(new Intent(MainActivity.this, ITR_form3.class));
                            } else if (choice.equals("form4")) {
                                startActivity(new Intent(MainActivity.this, ITR_form4.class));
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Wrong email or password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",mEmailEt.getText().toString());
                params.put("password",mPasswordEt.getText().toString());
                return  params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void setFormList() {

        mformType = findViewById(R.id.formType);
        String[] formList={"Select Form Type","form1","form2","form3","form4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item,formList);
        mformType.setAdapter(adapter);
    }
}
