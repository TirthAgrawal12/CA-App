package com.example.dell.siapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class ITR_form4 extends AppCompatActivity {

    CardView mPartA,mPartB,mPartAbtn,mPartBbtn;
    LinearLayout mRepresentativeAssesseeLayout;
    ImageView mPartAplusIcon,mPartBplusIcon;
    RadioGroup mRepresentative_assessee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itr_form4);

        //CardLayout
        mPartA = findViewById(R.id.PartAlayout);
        mPartB = findViewById(R.id.PartBlayout);
        mPartAbtn = findViewById(R.id.PartA);
        mPartBbtn = findViewById(R.id.PartB);
        //ImageView
        mPartAplusIcon = findViewById(R.id.PartAplusIcon);
        mPartBplusIcon = findViewById(R.id.PartBplusIcon);
        //RadioGroup
        mRepresentative_assessee = findViewById(R.id.ra);
        //LinearLayout
        mRepresentativeAssesseeLayout = findViewById(R.id.raLayout);



        //onclick events
        mPartAbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPartA.getVisibility()==View.VISIBLE){
                    mPartA.setVisibility(View.GONE);
                    mPartAplusIcon.setImageResource(R.drawable.add_icon);
                }else {
                    mPartAplusIcon.setImageResource(R.drawable.minus_icon);
                    mPartA.setVisibility(View.VISIBLE);
                }
            }
        });
        mPartBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPartB.getVisibility()==View.VISIBLE){
                    mPartBplusIcon.setImageResource(R.drawable.add_icon);
                    mPartB.setVisibility(View.GONE);
                }else {
                    mPartBplusIcon.setImageResource(R.drawable.minus_icon);
                    mPartB.setVisibility(View.VISIBLE);
                }
            }
        });
        mRepresentative_assessee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb1ra){
                    mRepresentativeAssesseeLayout.setVisibility(View.VISIBLE);
                }else {
                    mRepresentativeAssesseeLayout.setVisibility(View.GONE);
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
    }
}
