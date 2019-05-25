package com.example.sarnobatvipul.mydocapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarnobatvipul.mydocapp.R;
import com.example.sarnobatvipul.mydocapp.database.Patient;
import com.example.sarnobatvipul.mydocapp.ptProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class docHomeFragment extends Fragment {
    CardView editProfile;

    private TextView pt_name;
    private TextView pt_contact;
    private TextView pt_emailid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_doc_home,container,false);

        pt_name = (TextView)view.findViewById(R.id.pt_name);
        pt_contact=(TextView)view.findViewById(R.id.pt_contact);
        pt_emailid=(TextView)view.findViewById(R.id.pt_email);

        editProfile=(CardView)view.findViewById(R.id.edit_profile_card);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),ptProfileActivity.class);
                startActivity(in);
            }
        });


        try {
            stablevalues();
        }
        catch (Exception e)
        {

        }



        return view;




    }

    private void stablevalues()
    {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("DoctorData").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Patient p=dataSnapshot.getValue(Patient.class);

                pt_contact.setText(p.getContact());
                pt_emailid.setText(p.getEmailid());
                pt_name.setText(p.getName());

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {


            }
        });

    }
}


