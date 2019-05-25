package com.example.sarnobatvipul.mydocapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarnobatvipul.mydocapp.database.Reminder;
import com.example.sarnobatvipul.mydocapp.ptReminderdialog.dialog_days;
import com.example.sarnobatvipul.mydocapp.ptReminderdialog.dialog_dosage;
import com.example.sarnobatvipul.mydocapp.ptReminderdialog.dialog_duration;
import com.example.sarnobatvipul.mydocapp.ptReminderdialog.dialog_timing;
import com.example.sarnobatvipul.mydocapp.ptReminderdialog.dialog_title;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class ReminderActivity extends AppCompatActivity
        implements dialog_title.titleDialogListener,
        dialog_dosage.doseDialogListener,
        dialog_days.daysDialogListener,
        dialog_duration.durationDialogListener,
        dialog_timing.timingDialogListener


{

    private ImageView backarrow;

    private TextView title1,dose1,timing1,days1,duration1;
    private RelativeLayout title_ral,dose_ral,timing_ral,days_ral,duration_ral;


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
        setContentView(R.layout.activity_reminder);

        title1 = (TextView) findViewById(R.id.title_tv);
        dose1 = (TextView) findViewById(R.id.dose_tv);
        timing1 = (TextView) findViewById(R.id.timing_et);
        days1 = (TextView) findViewById(R.id.days_et);
        duration1 = (TextView) findViewById(R.id.duration_et);

        title_ral = (RelativeLayout) findViewById(R.id.title_rel);
        dose_ral = (RelativeLayout) findViewById(R.id.dose_rel);
        timing_ral = (RelativeLayout) findViewById(R.id.timing_rel);
        days_ral = (RelativeLayout) findViewById(R.id.days_rel);
        duration_ral = (RelativeLayout) findViewById(R.id.duration_rel);

        registre = (Button) findViewById(R.id.submitbtn);
/*
        try {
            stablevalues();
        }
        catch (Exception e)
        {

        }
*/
        registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ptProfileActivity.this,"xshxbshxs",Toast.LENGTH_SHORT).show();

                try {
                    String title=title1.getText().toString();
                    String dose=dose1.getText().toString();
                    String timing=timing1.getText().toString();
                    String days=days1.getText().toString();
                    String duration=duration1.getText().toString();

                    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reminder").child(firebaseUser.getUid());
                    String id = reference.push().getKey();
                    Reminder r = new Reminder(id,title,dose,timing,days,duration);
                    reference.setValue(r);
                    Toast.makeText(ReminderActivity.this, "Save Reminder", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(ReminderActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        title_ral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentitleDialog();
            }
        });
        dose_ral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendosageDialog();
            }
        });
        days_ral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendaysDialog();
            }
        });
        duration_ral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendurationDialog();
            }
        });
        timing_ral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    opentimingDialog();
            }
        });



    }
    //dialog open
    public void opentitleDialog(){
        dialog_title titledialog=new dialog_title();
        titledialog.show(getSupportFragmentManager(),"Title");

    }
    public void opendosageDialog(){
        dialog_dosage dosagedialog=new dialog_dosage();
        dosagedialog.show(getSupportFragmentManager(),"dosage");

    }
    public void opentimingDialog(){
        dialog_timing timingdialog=new dialog_timing();
        timingdialog.show(getSupportFragmentManager(),"timing");

    }
    public void opendaysDialog(){
        dialog_days daysdialog=new dialog_days();
        daysdialog.show(getSupportFragmentManager(),"days");

    }

    public void opendurationDialog(){
        dialog_duration durationdialog=new dialog_duration();
        durationdialog.show(getSupportFragmentManager(),"duration");

    }

    //apply text from dialog
    @Override
    public void applytitleText(String title) {
        title1.setText(title);
    }
    @Override
    public void applydoseText(String dose) {
        dose1.setText(dose);
    }
    @Override
    public void applytimingText(String timing) {
        timing1.setText(timing);
    }

    @Override
    public void applydaysText(String days) {
        days1.setText(days);
    }
    @Override
    public void applydurationText(String duration) {
        duration1.setText(duration);
    }

/*
    private void stablevalues()
    {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Reminder").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Reminder r=dataSnapshot.getValue(Reminder.class);
                title1.setText(r.getTitle());
                dose1.setText(r.getDose());
                timing1.setText(r.getTiming());
                days1.setText(r.getDays());
                duration1.setText(r.getDuration());
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(ReminderActivity.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }

*/

}
