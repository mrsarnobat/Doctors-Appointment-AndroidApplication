package com.example.sarnobatvipul.mydocapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sarnobatvipul.mydocapp.DisplayProfileActivity;
import com.example.sarnobatvipul.mydocapp.R;
import com.example.sarnobatvipul.mydocapp.database.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by sarno on 24-04-2019.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private Context mContext;
    private List<Doctor> mdoctors;

    private FirebaseUser firebaseUser;

    public DoctorAdapter(Context mContext,List<Doctor> mdoctors) {
        this.mContext=mContext;
        this.mdoctors = mdoctors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.doctors_item, viewGroup, false);
        return new DoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final Doctor doctor=mdoctors.get(i);
        viewHolder.name.setText(doctor.getName());
        viewHolder.contact.setText(doctor.getContact());
        viewHolder.addr.setText(doctor.getAddress());

/*
        if (doctor.getId().equals(firebaseUser.getUid())){
            viewHolder.btn_apt.setVisibility(View.GONE);
        }
*/
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,DisplayProfileActivity.class);
                i.putExtra("id",doctor.getId());
                mContext.startActivity(i);
            }
        });




    }

    @Override
    public int getItemCount() {
        return mdoctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name,addr,contact;
        public Button btn_apt;
       // public CircleImageView image_profile;


        public ViewHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.docname);
            addr=itemView.findViewById(R.id.docaddr);
            contact=itemView.findViewById(R.id.doccontact);
         //   image_profile=itemView.findViewById(R.id.profile_image);

        }
    }

}
