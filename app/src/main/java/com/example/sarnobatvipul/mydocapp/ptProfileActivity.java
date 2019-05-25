package com.example.sarnobatvipul.mydocapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarnobatvipul.mydocapp.database.Patient;
import com.example.sarnobatvipul.mydocapp.ptReminderdialog.dialog_title;
import com.example.sarnobatvipul.mydocapp.ptprofiledialog.dialog_ptaddr;
import com.example.sarnobatvipul.mydocapp.ptprofiledialog.dialog_ptbldgrp;
import com.example.sarnobatvipul.mydocapp.ptprofiledialog.dialog_ptcontact;
import com.example.sarnobatvipul.mydocapp.ptprofiledialog.dialog_ptemailid;
import com.example.sarnobatvipul.mydocapp.ptprofiledialog.dialog_ptgender;
import com.example.sarnobatvipul.mydocapp.ptprofiledialog.dialog_ptname;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ptProfileActivity extends AppCompatActivity implements dialog_ptname.ptnameDialogListener,
        dialog_ptcontact.ptcontactDialogListener,
        dialog_ptemailid.ptemailidDialogListener,
        dialog_ptgender.ptgenderDialogListener,
        dialog_ptbldgrp.ptbldgrpDialogListener,
        dialog_ptaddr.ptaddrDialogListener
{

    private ImageView backarrow;

    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE=1;
    Uri imageUri;
    private TextView pt_name;
    private RelativeLayout pt_name_rel;

    private TextView pt_contact;
    private RelativeLayout pt_contact_rel;

    private TextView pt_emailid;
    private RelativeLayout pt_emaild_rel;

    private TextView pt_gender;
    private RelativeLayout pt_gender_rel;

    private TextView pt_dob;
    private RelativeLayout pt_dob_rel;
    private DatePickerDialog datePickerDialog;
    int year,month,dayofmonth;
    private Calendar calender;

    private TextView pt_bldgrp;
    private RelativeLayout pt_bldgrp_rel;

    private TextView pt_addr;
    private RelativeLayout pt_addr_rel;

    private Button registre;

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
        setContentView(R.layout.activity_pt_profile);
        backarrow = (ImageView) findViewById(R.id.backbtn);


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ptProfileActivity.this, ptHomePage_Activity.class));
            }
        });


        //  ProfileImage=(CircleImageView)findViewById(R.id.Profile_Image);

        pt_name = (TextView) findViewById(R.id.patientname_tv);
        pt_name_rel = (RelativeLayout) findViewById(R.id.patientname_rel);


        pt_contact = (TextView) findViewById(R.id.patientcontact_tv);
        pt_contact_rel = (RelativeLayout) findViewById(R.id.patientcontact_rel);

        pt_emailid = (TextView) findViewById(R.id.patientemail_et);
        pt_emaild_rel = (RelativeLayout) findViewById(R.id.patientemail_rel);

        pt_gender = (TextView) findViewById(R.id.patientgender_et);
        pt_gender_rel = (RelativeLayout) findViewById(R.id.patientgender_rel);

        pt_dob = (TextView) findViewById(R.id.patientdob_et);
        pt_dob_rel = (RelativeLayout) findViewById(R.id.patientdob_rel);

        pt_bldgrp = (TextView) findViewById(R.id.patientbldgrp_et);
        pt_bldgrp_rel = (RelativeLayout) findViewById(R.id.patientbldgrp_rel);

        pt_addr = (TextView) findViewById(R.id.patientaddr_et);
        pt_addr_rel = (RelativeLayout) findViewById(R.id.patientaddr_rel);


        registre = (Button) findViewById(R.id.submitbtn);

        try {
            stablevalues();
        }
        catch (Exception e)
        {

        }


        registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ptProfileActivity.this,"xshxbshxs",Toast.LENGTH_SHORT).show();

                try {
                    String name=pt_name.getText().toString();
                    String contact = pt_contact.getText().toString();
                    String emailid = pt_emailid.getText().toString();
                    String gender = pt_gender.getText().toString();
                    String dob = pt_dob.getText(). toString();
                    String bldgrp = pt_bldgrp.getText().toString();
                    String addr = pt_addr.getText().toString();

                    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PatientData").child(firebaseUser.getUid());
                    String id = reference.push().getKey();
                    Patient f = new Patient(id, contact, emailid, gender, dob, bldgrp, addr,name);
                    reference.setValue(f);
                    Toast.makeText(ptProfileActivity.this, "Feed Receive..!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(ptProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        pt_name_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openptnameDialog();
            }
        });

        pt_contact_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openptcontactDialog();
            }
        });

        pt_emaild_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openptemailidDialog();
            }
        });

        pt_gender_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openptgenderDialog();
            }
        });

        pt_dob_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender=calender.getInstance();
                year=calender.get(Calendar.YEAR);
                month=calender.get(Calendar.MONTH);
                dayofmonth=calender.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(ptProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        pt_dob.setText(dayOfMonth+"-"+month+"-"+year);
                    }
                },year,month,dayofmonth);
                datePickerDialog.show();
            }
        });

        pt_bldgrp_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openptbldgrpDialog();
            }
        });

        pt_addr_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openptaddrDialog();
            }
        });





    }

    private void stablevalues()
    {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PatientData").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Patient p=dataSnapshot.getValue(Patient.class);
                pt_contact.setText(p.getContact());
                pt_addr.setText(p.getAddress());
                pt_dob.setText(p.getDob());
                pt_name.setText(p.getName());
                pt_emailid.setText(p.getEmailid());
                pt_gender.setText(p.getGender());
                pt_bldgrp.setText(p.getBloodgroup());
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(ptProfileActivity.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }

    //dialog open
    public void openptnameDialog(){
        dialog_title ptnamedialog=new dialog_title();
        ptnamedialog.show(getSupportFragmentManager(),"Patient Name");

    }

    public void openptcontactDialog(){
        dialog_ptcontact ptcontactdialog=new dialog_ptcontact();
        ptcontactdialog.show(getSupportFragmentManager(),"Patient Contact");

    }

    public void openptemailidDialog(){
        dialog_ptemailid ptemailiddialog=new dialog_ptemailid();
        ptemailiddialog.show(getSupportFragmentManager(),"Patient Email ID");
    }

    public void openptgenderDialog(){
        dialog_ptgender ptgenderdialog=new dialog_ptgender();
        ptgenderdialog.show(getSupportFragmentManager(),"Patient Gender");
    }

    public void openptbldgrpDialog(){
        dialog_ptbldgrp ptbldgrpdialog=new dialog_ptbldgrp();
        ptbldgrpdialog.show(getSupportFragmentManager(),"Patient Blood Group");
    }

    public void openptaddrDialog(){
        dialog_ptaddr ptaddrdialog=new dialog_ptaddr();
        ptaddrdialog.show(getSupportFragmentManager(),"Patient Address");
    }


    //apply text from dialog
    @Override
    public void applyptnameText(String ptname) {
        pt_name.setText(ptname);
    }

    @Override
    public void applyptcontactText(String ptcontact) {
        pt_contact.setText(ptcontact);
    }

    @Override
    public void applyptemailidText(String ptemailid) {
        pt_emailid.setText(ptemailid);
    }

    @Override
    public void applyptgenderText(String ptgender) {
        pt_gender.setText(ptgender);
    }

    @Override
    public void applyptbldgrpText(String ptbldgrp){
        pt_bldgrp.setText(ptbldgrp);
    }
    @Override
    public void applyptaddrText(String addr){
        pt_addr.setText(addr);
    }



}
