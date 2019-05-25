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

import com.example.sarnobatvipul.mydocapp.database.Doctor;
import com.example.sarnobatvipul.mydocapp.docdialog.dialog_docaddr;
import com.example.sarnobatvipul.mydocapp.docdialog.dialog_docbldgrp;
import com.example.sarnobatvipul.mydocapp.docdialog.dialog_doccontact;
import com.example.sarnobatvipul.mydocapp.docdialog.dialog_docemailid;
import com.example.sarnobatvipul.mydocapp.docdialog.dialog_docgender;
import com.example.sarnobatvipul.mydocapp.docdialog.dialog_docname;
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


public class docProfileActivity extends AppCompatActivity implements dialog_docname.docnameDialogListener,
        dialog_doccontact.doccontactDialogListener,
        dialog_docemailid.docemailidDialogListener,
        dialog_docgender.docgenderDialogListener,
        dialog_docbldgrp.docbldgrpDialogListener,
        dialog_docaddr.docaddrDialogListener

{

    private ImageView backarrow;

    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE=1;
    Uri imageUri;
    private TextView doc_name;
    private RelativeLayout doc_name_rel;

    private TextView doc_contact;
    private RelativeLayout doc_contact_rel;

    private TextView doc_emailid;
    private RelativeLayout doc_emaild_rel;

    private TextView doc_gender;
    private RelativeLayout doc_gender_rel;

    private TextView doc_dob;
    private RelativeLayout doc_dob_rel;
    private DatePickerDialog datePickerDialog;
    int year,month,dayofmonth;
    private Calendar calender;

    private TextView doc_bldgrp;
    private RelativeLayout doc_bldgrp_rel;

    private TextView doc_addr;
    private RelativeLayout doc_addr_rel;

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
        setContentView(R.layout.activity_doc_profile);
        backarrow = (ImageView) findViewById(R.id.backbtn);


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(docProfileActivity.this, docHomePage_Activity.class));
            }
        });


        //  ProfileImage=(CircleImageView)findViewById(R.id.Profile_Image);

        doc_name = (TextView) findViewById(R.id.doctorname_tv);
        doc_name_rel = (RelativeLayout) findViewById(R.id.doctorname_rel);


        doc_contact = (TextView) findViewById(R.id.doctorcontact_tv);
        doc_contact_rel = (RelativeLayout) findViewById(R.id.doctorcontact_rel);

        doc_emailid = (TextView) findViewById(R.id.doctoremail_et);
        doc_emaild_rel = (RelativeLayout) findViewById(R.id.doctoremail_rel);

        doc_gender = (TextView) findViewById(R.id.doctorgender_et);
        doc_gender_rel = (RelativeLayout) findViewById(R.id.doctorgender_rel);

        doc_dob = (TextView) findViewById(R.id.doctordob_et);
        doc_dob_rel = (RelativeLayout) findViewById(R.id.doctordob_rel);

        doc_bldgrp = (TextView) findViewById(R.id.doctorbldgrp_et);
        doc_bldgrp_rel = (RelativeLayout) findViewById(R.id.doctorbldgrp_rel);

        doc_addr = (TextView) findViewById(R.id.doctoraddr_et);
        doc_addr_rel = (RelativeLayout) findViewById(R.id.doctoraddr_rel);


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
                    String name=doc_name.getText().toString();
                    String contact = doc_contact.getText().toString();
                    String emailid = doc_emailid.getText().toString();
                    String gender = doc_gender.getText().toString();
                    String dob = doc_dob.getText(). toString();
                    String bldgrp = doc_bldgrp.getText().toString();
                    String addr = doc_addr.getText().toString();

                    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("DoctorData").child(firebaseUser.getUid());
                    String id = reference.push().getKey();
                    Doctor f = new Doctor(id,contact,emailid,gender,dob,bldgrp,addr,name);
                    reference.setValue(f);
                    Toast.makeText(docProfileActivity.this, "Feed Receive..!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(docProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        doc_name_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendocnameDialog();
            }
        });

        doc_contact_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendoccontactDialog();
            }
        });

        doc_emaild_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendocemailidDialog();
            }
        });

        doc_gender_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendocgenderDialog();
            }
        });

        doc_dob_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender=calender.getInstance();
                year=calender.get(Calendar.YEAR);
                month=calender.get(Calendar.MONTH);
                dayofmonth=calender.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(docProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        doc_dob.setText(dayOfMonth+"-"+month+"-"+year);
                    }
                },year,month,dayofmonth);
                datePickerDialog.show();
            }
        });

        doc_bldgrp_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendocbldgrpDialog();
            }
        });

        doc_addr_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendocaddrDialog();
            }
        });





    }

    private void stablevalues()
    {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("DoctorData").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Doctor p=dataSnapshot.getValue(Doctor.class);
                doc_contact.setText(p.getContact());
                doc_addr.setText(p.getAddress());
                doc_dob.setText(p.getDob());
                doc_name.setText(p.getName());
                doc_emailid.setText(p.getEmailid());
                doc_gender.setText(p.getGender());
                doc_bldgrp.setText(p.getBloodgroup());
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(docProfileActivity.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }

    //dialog open
    public void opendocnameDialog(){
        dialog_docname docnamedialog=new dialog_docname();
        docnamedialog.show(getSupportFragmentManager(),"Doctor Name");

    }

    public void opendoccontactDialog(){
        dialog_doccontact doccontactdialog=new dialog_doccontact();
        doccontactdialog.show(getSupportFragmentManager(),"Doctor Contact");

    }

    public void opendocemailidDialog(){
        dialog_docemailid docemailiddialog=new dialog_docemailid();
        docemailiddialog.show(getSupportFragmentManager(),"Doctor Email ID");
    }

    public void opendocgenderDialog(){
        dialog_docgender docgenderdialog=new dialog_docgender();
        docgenderdialog.show(getSupportFragmentManager(),"Doctor Gender");
    }

    public void opendocbldgrpDialog(){
        dialog_docbldgrp docbldgrpdialog=new dialog_docbldgrp();
        docbldgrpdialog.show(getSupportFragmentManager(),"Doctor Blood Group");
    }

    public void opendocaddrDialog(){
        dialog_docaddr docaddrdialog=new dialog_docaddr();
        docaddrdialog.show(getSupportFragmentManager(),"Doctor Address");
    }


    //apply text from dialog
    @Override
    public void applydocnameText(String docname) {
        doc_name.setText(docname);
    }

    @Override
    public void applydoccontactText(String doccontact) {
        doc_contact.setText(doccontact);
    }

    @Override
    public void applydocemailidText(String docemailid) {
        doc_emailid.setText(docemailid);
    }

    @Override
    public void applydocgenderText(String docgender) {
        doc_gender.setText(docgender);
    }

    @Override
    public void applydocbldgrpText(String docbldgrp){
        doc_bldgrp.setText(docbldgrp);
    }
    @Override
    public void applydocaddrText(String addr){
        doc_addr.setText(addr);
    }



}
