package com.example.sarnobatvipul.mydocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class SignInActivity extends AppCompatActivity {


    CardView card_doc;
    CardView card_pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        card_doc=(CardView)findViewById(R.id.cardView_Doc);
        card_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignIn_Doc_Activity.class));
            }
        });

        card_pt=(CardView)findViewById(R.id.cardView_Pt);
        card_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignIn_Pt_Activity.class));
            }
        });


    }
}
