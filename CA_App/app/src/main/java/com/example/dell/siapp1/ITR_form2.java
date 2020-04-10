package com.example.dell.siapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class ITR_form2 extends AppCompatActivity {

    LinearLayout mfsd_layout,mfsd2_layout;
    CheckBox mfsd , mfsd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itr_form2);

        //LinearLayout
        mfsd_layout = findViewById(R.id.fsd_layout);
        mfsd2_layout = findViewById(R.id.fsd2_layout);

        //CheckBox
        mfsd = findViewById(R.id.FSd);
        mfsd2 = findViewById(R.id.FSd2);

        mfsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    mfsd_layout.setVisibility(View.VISIBLE);
                }else {
                    mfsd_layout.setVisibility(View.GONE);
                }
            }
        });
        mfsd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()){
                    mfsd2_layout.setVisibility(View.VISIBLE);
                }else {
                    mfsd2_layout.setVisibility(View.GONE);
                }
            }
        });

    }
}
