package com.example.dell.siapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ITR_form1 extends AppCompatActivity {

    CardView mPartA, mPartB1, mPartB2, mPartB3, mPartAbtn, mPartBbtn;
    ImageView mPartAplusIcon, mPartBplusIcon;
    String UserEmail;
    //---------------------------------------------------------------
    Button mSubmitBtn;

    //Part A
    EditText mPanEt, mNameEt,mDateOfBirth, maadhharNoEt, maadhharEnrolmentIdET, mmobileNoET, memailET,
            maddressET, mcityET, mstateET, mcountryET, mpinCodeET, mreceiptNoET,
            mfillingOriginalReturnDateET, muniqueNoET, morderDateET;
    //Part B
    EditText mb1aET, mb1bET, mb1cET, mb1iiET, mb1ivaET, mb1ivbET, mb1ivcET, mb2iET, mb2iiET,
            mb2vET, mb2viET, mb3aET, mb3bET;
    RadioGroup mfiledUsEt , mnoticeUsEt ,mnatureOfEmployeeEt;
    String sfiledUsEt , snoticeUsEt ,snatureOfEmployeeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itr_form1);
        UserEmail = getIntent().getExtras().get("email").toString();
        Toast.makeText(this, "email = "+UserEmail, Toast.LENGTH_SHORT).show();
        mPartA = findViewById(R.id.PartAlayout);
        mPartB1 = findViewById(R.id.PartB1layout);
        mPartB2 = findViewById(R.id.PartB2layout);
        mPartB3 = findViewById(R.id.PartB3layout);
        mPartAbtn = findViewById(R.id.PartA);
        mPartBbtn = findViewById(R.id.PartB);
        mSubmitBtn = findViewById(R.id.submitBtn);

        mPartAplusIcon = findViewById(R.id.PartAplusIcon);
        mPartBplusIcon = findViewById(R.id.PartBplusIcon);

        editTextDeclaration();
        mPartAbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPartA.getVisibility() == View.VISIBLE) {
                    mPartAplusIcon.setImageResource(R.drawable.add_icon);
                    mPartA.setVisibility(View.GONE);
                } else {
                    mPartAplusIcon.setImageResource(R.drawable.minus_icon);
                    mPartA.setVisibility(View.VISIBLE);
                }
            }
        });
        mPartBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPartB1.getVisibility() == View.VISIBLE) {
                    mPartBplusIcon.setImageResource(R.drawable.add_icon);
                    mPartB1.setVisibility(View.GONE);
                    mPartB2.setVisibility(View.GONE);
                    mPartB3.setVisibility(View.GONE);
                } else {
                    mPartBplusIcon.setImageResource(R.drawable.minus_icon);
                    mPartB1.setVisibility(View.VISIBLE);
                    mPartB2.setVisibility(View.VISIBLE);
                    mPartB3.setVisibility(View.VISIBLE);
                }
            }
        });
        mPartAplusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mPartBplusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//--------------------------------------------------------------------------

        mfiledUsEt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                sfiledUsEt = radioButton.getText().toString();
            }
        });
        mnoticeUsEt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                snoticeUsEt = radioButton.getText().toString();
            }
        });
        mnatureOfEmployeeEt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                snatureOfEmployeeEt = radioButton.getText().toString();
            }
        });
        getUserData();

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

                //Part A
                mPanEt.setEnabled(false);
                mNameEt.setEnabled(false);
                mDateOfBirth.setEnabled(false);
                maadhharNoEt.setEnabled(false);
                maadhharEnrolmentIdET.setEnabled(false);
                mmobileNoET.setEnabled(false);
                memailET.setEnabled(false);
                maddressET.setEnabled(false);
                mcityET.setEnabled(false);
                mstateET.setEnabled(false);
                mcountryET.setEnabled(false);
                mpinCodeET.setEnabled(false);
                mreceiptNoET.setEnabled(false);
                mfillingOriginalReturnDateET.setEnabled(false);
                muniqueNoET.setEnabled(false);
                morderDateET.setEnabled(false);

                //Part B
                mb1aET.setEnabled(false);
                mb1bET.setEnabled(false);
                mb1cET.setEnabled(false);
                mb1iiET.setEnabled(false);
                mb1ivaET.setEnabled(false);
                mb1ivbET.setEnabled(false);
                mb1ivcET.setEnabled(false);
                mb2iET.setEnabled(false);
                mb2iiET.setEnabled(false);
                mb2vET.setEnabled(false);
                mb2viET.setEnabled(false);
                mb3aET.setEnabled(false);
                mb3bET.setEnabled(false);

;

            }
        });
    }

    private void editTextDeclaration() {
        //Part A
        mPanEt = findViewById(R.id.panET);
        mNameEt = findViewById(R.id.nameET);
        mDateOfBirth = findViewById(R.id.dobET);
        maadhharNoEt = findViewById(R.id.aadhharNoET);
        maadhharEnrolmentIdET = findViewById(R.id.aadhharEnrolmentIdET);
        mmobileNoET = findViewById(R.id.mobileNoET);
        memailET = findViewById(R.id.emailET);
        maddressET = findViewById(R.id.addressET);
        mcityET = findViewById(R.id.cityET);
        mstateET = findViewById(R.id.stateET);
        mcountryET = findViewById(R.id.countryET);
        mpinCodeET = findViewById(R.id.pinCodeET);
        mreceiptNoET = findViewById(R.id.receiptNoET);
        mfillingOriginalReturnDateET = findViewById(R.id.fillingOriginalReturnDateET);
        muniqueNoET = findViewById(R.id.uniqueNoET);
        morderDateET = findViewById(R.id.orderDateET);

        //Part B
        mb1aET = findViewById(R.id.b1aET);
        mb1bET = findViewById(R.id.b1bET);
        mb1cET = findViewById(R.id.b1cET);
        mb1iiET = findViewById(R.id.b1iiET);
        mb1ivaET = findViewById(R.id.b1ivaET);
        mb1ivbET = findViewById(R.id.b1ivbET);
        mb1ivcET = findViewById(R.id.b1ivcET);
        mb2iET = findViewById(R.id.b2iET);
        mb2iiET = findViewById(R.id.b2iiET);
        mb2vET = findViewById(R.id.b2vET);
        mb2viET = findViewById(R.id.b2viET);
        mb3aET = findViewById(R.id.b3aET);
        mb3bET = findViewById(R.id.b3bET);


        mnatureOfEmployeeEt = findViewById(R.id.natureOfEmployeeEt);
        mfiledUsEt = findViewById(R.id.filedUsEt);
        mnoticeUsEt = findViewById(R.id.noticeUsEt);


        //Part A
        mPanEt.setEnabled(false);
        mNameEt.setEnabled(false);
        mDateOfBirth.setEnabled(false);
        maadhharNoEt.setEnabled(false);
        maadhharEnrolmentIdET.setEnabled(false);
        mmobileNoET.setEnabled(false);
        memailET.setEnabled(false);
        maddressET.setEnabled(false);
        mcityET.setEnabled(false);
        mstateET.setEnabled(false);
        mcountryET.setEnabled(false);
        mpinCodeET.setEnabled(false);
        mreceiptNoET.setEnabled(false);
        mfillingOriginalReturnDateET.setEnabled(false);
        muniqueNoET.setEnabled(false);
        morderDateET.setEnabled(false);

        //Part B
        mb1aET.setEnabled(false);
        mb1bET.setEnabled(false);
        mb1cET.setEnabled(false);
        mb1iiET.setEnabled(false);
        mb1ivaET.setEnabled(false);
        mb1ivbET.setEnabled(false);
        mb1ivcET.setEnabled(false);
        mb2iET.setEnabled(false);
        mb2iiET.setEnabled(false);
        mb2vET.setEnabled(false);
        mb2viET.setEnabled(false);
        mb3aET.setEnabled(false);
        mb3bET.setEnabled(false);

    }

    private void getUserData() {
        String URL = "http://localhost/si/retriveUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ITR_form1.this, "Loading Data.", Toast.LENGTH_SHORT).show();

                        try {
                            JSONArray users = new JSONArray(response);
                            JSONObject object = users.getJSONObject(0);

                            //Part A
                            mPanEt.setText(object.getString("Pan"));
                            mNameEt.setText(object.getString("Name"));
                            mDateOfBirth.setText(object.getString("DateOfBirth"));
                            maadhharNoEt.setText(object.getString("aadhharNo"));
                            maadhharEnrolmentIdET.setText(object.getString("aadhharEnrolmentId"));
                            mmobileNoET.setText(object.getString("mobileNo"));
                            memailET.setText(object.getString("email"));
                            maddressET.setText(object.getString("address"));
                            mcityET.setText(object.getString("city"));
                            mstateET.setText(object.getString("state"));
                            mcountryET.setText(object.getString("country"));
                            mpinCodeET.setText(object.getString("pinCode"));
                            mreceiptNoET.setText(object.getString("receiptNo"));
                            mfillingOriginalReturnDateET.setText(object.getString("fillingOriginalReturnDate"));
                            muniqueNoET.setText(object.getString("uniqueNo"));
                            morderDateET.setText(object.getString("orderDate"));

                            //Part B
                            mb1aET.setText(object.getString("b1a"));
                            mb1bET.setText(object.getString("b1b"));
                            mb1cET.setText(object.getString("b1c"));
                            mb1iiET.setText(object.getString("b1ii"));
                            mb1ivaET.setText(object.getString("b1iva"));
                            mb1ivbET.setText(object.getString("b1ivb"));
                            mb1ivcET.setText(object.getString("b1ivc"));
                            mb2iET.setText(object.getString("b2i"));
                            mb2iiET.setText(object.getString("b2ii"));
                            mb2vET.setText(object.getString("b2v"));
                            mb2viET.setText(object.getString("b2vi"));
                            mb3aET.setText(object.getString("b3a"));
                            mb3bET.setText(object.getString("b3b"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ITR_form1.this, "" + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ITR_form1.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String UserEmail = getIntent().getExtras().get("email").toString();
                params.put("email", UserEmail);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }


    private void registerUser() {
        //first we will do the validations

validate();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://localhost/si/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("1")) {
                            Toast.makeText(ITR_form1.this, "Data Updated.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ITR_form1.this, "Error in Updating Data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ITR_form1.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Pan", mPanEt.getText().toString());
                params.put("Name", mNameEt.getText().toString());
                params.put("DateOfBirth", mDateOfBirth.getText().toString());
                params.put("aadhharNo", maadhharNoEt.getText().toString());
                params.put("aadhharEnrolmentId", maadhharEnrolmentIdET.getText().toString());
                params.put("mobileNo", mmobileNoET.getText().toString());
                params.put("email", memailET.getText().toString());
                params.put("address", maddressET.getText().toString());
                params.put("city", mcityET.getText().toString());
                params.put("state", mstateET.getText().toString());
                params.put("country", mcountryET.getText().toString());
                params.put("pinCode", mpinCodeET.getText().toString());
                params.put("receiptNo", mreceiptNoET.getText().toString());
                params.put("fillingOriginalReturnDate", mfillingOriginalReturnDateET.getText().toString());
                params.put("uniqueNo", muniqueNoET.getText().toString());
                params.put("orderDate", morderDateET.getText().toString());
                params.put("b1a", mb1aET.getText().toString());
                params.put("b1b", mb1bET.getText().toString());
                params.put("b1c", mb1cET.getText().toString());
                params.put("b1ii", mb1iiET.getText().toString());
                params.put("b1iva", mb1ivaET.getText().toString());
                params.put("b1ivb", mb1ivbET.getText().toString());
                params.put("b1ivc", mb1ivcET.getText().toString());
                params.put("b2i", mb2iET.getText().toString());
                params.put("b2ii", mb2iiET.getText().toString());
                params.put("b2v", mb2vET.getText().toString());
                params.put("b2vi", mb2viET.getText().toString());
                params.put("b3a", mb3aET.getText().toString());
                params.put("b3b", mb3bET.getText().toString());

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void validate() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.formmenu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        final MenuItem mEdit = menu.findItem(R.id.Edit);

        mEdit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //Part A
                mPanEt.setEnabled(true);
                mNameEt.setEnabled(true);
                mDateOfBirth.setEnabled(true);
                maadhharNoEt.setEnabled(true);
                maadhharEnrolmentIdET.setEnabled(true);
                mmobileNoET.setEnabled(true);
                memailET.setEnabled(true);
                maddressET.setEnabled(true);
                mcityET.setEnabled(true);
                mstateET.setEnabled(true);
                mcountryET.setEnabled(true);
                mpinCodeET.setEnabled(true);
                mreceiptNoET.setEnabled(true);
                mfillingOriginalReturnDateET.setEnabled(true);
                muniqueNoET.setEnabled(true);
                morderDateET.setEnabled(true);
                //Part B
                mb1aET.setEnabled(true);
                mb1bET.setEnabled(true);
                mb1cET.setEnabled(true);
                mb1iiET.setEnabled(true);
                mb1ivaET.setEnabled(true);
                mb1ivbET.setEnabled(true);
                mb1ivcET.setEnabled(true);
                mb2iET.setEnabled(true);
                mb2iiET.setEnabled(true);
                mb2vET.setEnabled(true);
                mb2viET.setEnabled(true);
                mb3aET.setEnabled(true);
                mb3bET.setEnabled(true);

                mSubmitBtn.setVisibility(View.VISIBLE);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.Edit) {
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
