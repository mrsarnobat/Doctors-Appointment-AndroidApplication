package com.example.sarnobatvipul.mydocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarnobatvipul.mydocapp.database.Doctor;
import com.example.sarnobatvipul.mydocapp.database.DoctorAppointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class DisplayAProfileActivity extends AppCompatActivity {

    private ImageView mProfileImage;
    private TextView mProfileName, mProfileStatus, mProfileFriendsCount;
    private Button mProfileCancelReqBtn;


    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;

    public static final int IMAGE_REQUEST=1;
    public static final int RESULT_OF=1;
    private StorageTask storageTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_aprofile);




        mProfileImage=(ImageView)findViewById(R.id.profile_image);
        mProfileName=(TextView) findViewById(R.id.profile_displayName);
        mProfileStatus=(TextView)findViewById(R.id.profile_status);
        mProfileFriendsCount=(TextView)findViewById(R.id.profile_totalFriend);
        mProfileCancelReqBtn=(Button)findViewById(R.id.profile_decline_btn);



        callingDoctors();



        mProfileCancelReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try {
                    Intent i = getIntent();
                    String name=mProfileName.getText().toString();
                    String pi="default";
                    String addr=mProfileStatus.getText().toString();
                    String contact=mProfileFriendsCount.getText().toString();
                    String getid = i.getStringExtra("id");
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("DoctorsAppointment");
                    DoctorAppointment d= new DoctorAppointment(getid,name,pi,addr,contact);
                    reference.child(getid).removeValue();
                    Toast.makeText(DisplayAProfileActivity.this,"Cancel Appointment to this doctor:)",Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(DisplayAProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void callingDoctors()

    {
        Intent intent;
        intent=getIntent();
        String uid;
        uid=intent.getStringExtra("id");
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("DoctorData").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Doctor i=dataSnapshot.getValue(Doctor.class);
                try {
                    mProfileName.setText(i.getName());
                    mProfileStatus.setText(i.getAddress());
                    mProfileFriendsCount.setText(i.getContact());

                }
                catch (Exception e)
                {
                    Toast.makeText(DisplayAProfileActivity.this,"Try Again..This is not valid now",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(DisplayAProfileActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}
