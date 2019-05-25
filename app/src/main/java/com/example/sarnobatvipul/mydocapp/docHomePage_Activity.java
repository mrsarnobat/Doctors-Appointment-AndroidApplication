package com.example.sarnobatvipul.mydocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.sarnobatvipul.mydocapp.Fragment.docAppointmentFragment;
import com.example.sarnobatvipul.mydocapp.Fragment.docHomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class docHomePage_Activity extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment=null;
    ImageView logout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_home_page);
        logout=(ImageView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(docHomePage_Activity.this,SignInActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

        bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new docHomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId())
                    {
                        case R.id.tab_home:
                            selectedFragment=new docHomeFragment();
                            break;
                        case R.id.tab_appointment:
                            selectedFragment=new docAppointmentFragment();
                            break;
                        case R.id.tab_profile:
                            selectedFragment=null;
                            startActivity(new Intent(docHomePage_Activity.this,docProfileActivity.class));
                            break;
                    }

                    if (selectedFragment!=null)
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    }



                    return true;
                }
            };





}
