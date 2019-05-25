package com.example.sarnobatvipul.mydocapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sarnobatvipul.mydocapp.R;
import com.example.sarnobatvipul.mydocapp.adapter.DoctorAppointmentAdapter;
import com.example.sarnobatvipul.mydocapp.database.Doctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class docAppointmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private DoctorAppointmentAdapter doctorAppointmentAdapter;
    private List<Doctor> mDoctors;

    EditText search_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doc_appointment, container, false);

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        search_bar=view.findViewById(R.id.search_bar);

        mDoctors=new ArrayList<>();
        doctorAppointmentAdapter=new DoctorAppointmentAdapter(getContext(),mDoctors);
        recyclerView.setAdapter(doctorAppointmentAdapter);

        readDoctors();
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchDoctors(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    private void searchDoctors(String s){
        Query query= FirebaseDatabase.getInstance().getReference("DoctorsAppointment").orderByChild("name")
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDoctors.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Doctor doctor=snapshot.getValue(Doctor.class);
                    mDoctors.add(doctor);
                }
                doctorAppointmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void readDoctors(){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("DoctorsAppointment");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (search_bar.getText().toString().equals("")){
                    mDoctors.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Doctor doctor=snapshot.getValue(Doctor.class);
                        mDoctors.add(doctor);
                    }
                    doctorAppointmentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}