package com.example.sarnobatvipul.mydocapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sarnobatvipul.mydocapp.R;
import com.example.sarnobatvipul.mydocapp.ReminderActivity;
import com.example.sarnobatvipul.mydocapp.adapter.ReminderAdapter;
import com.example.sarnobatvipul.mydocapp.database.Reminder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ptReminderFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ReminderAdapter reminderAdapter;
    private List<Reminder> mReminders;
    EditText search_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pt_reminder, container, false);

        fab=(FloatingActionButton)view.findViewById(R.id.addreminder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getActivity(),ReminderActivity.class);

                startActivity(in);
            }
        });

        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //search_bar=view.findViewById(R.id.search_bar);

        mReminders=new ArrayList<>();
        reminderAdapter=new ReminderAdapter(getContext(),mReminders);
        recyclerView.setAdapter(reminderAdapter);

        readReminder();
/*
        search_bar=view.findViewById(R.id.search_bar);
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchReminder(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
*/



        return view;
    }
   /* private void searchReminder(String s){
        Query query= FirebaseDatabase.getInstance().getReference("Reminder").orderByChild("name")
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mReminders.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Reminder reminder=snapshot.getValue(Reminder.class);
                    mReminders.add(reminder);
                }
                reminderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
*/
    private void readReminder(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Reminder");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // if (search_bar.getText().toString().equals("")) {
                    mReminders.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Reminder reminder = snapshot.getValue(Reminder.class);
                        mReminders.add(reminder);
                    }
                    reminderAdapter.notifyDataSetChanged();
                //}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
