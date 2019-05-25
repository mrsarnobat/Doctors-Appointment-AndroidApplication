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

import com.example.sarnobatvipul.mydocapp.R;
import com.example.sarnobatvipul.mydocapp.ReminderActivity;
import com.example.sarnobatvipul.mydocapp.database.Reminder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by sarno on 24-04-2019.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    private Context mContext;
    private List<Reminder> mReminders;

    private FirebaseUser firebaseUser;

    public ReminderAdapter(Context mContext, List<Reminder> mReminders) {
        this.mContext=mContext;
        this.mReminders = mReminders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.reminders_item, viewGroup, false);
        return new ReminderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final Reminder reminder=mReminders.get(i);
        viewHolder.title.setText(reminder.getTitle());
        viewHolder.dose.setText(reminder.getDose());
        viewHolder.timing.setText(reminder.getTiming());
        viewHolder.days.setText(reminder.getDays());
        viewHolder.duration.setText(reminder.getDuration());


/*
        if (doctor.getId().equals(firebaseUser.getUid())){
            viewHolder.btn_apt.setVisibility(View.GONE);
        }
*/
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,ReminderActivity.class);
                i.putExtra("id",reminder.getId());
                mContext.startActivity(i);
            }
        });




    }

    @Override
    public int getItemCount() {
        return mReminders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title,dose,timing,days,duration;
        public Button btn_apt;
       // public CircleImageView image_profile;


        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            dose=itemView.findViewById(R.id.dose);
            timing=itemView.findViewById(R.id.timing);
            days=itemView.findViewById(R.id.days);
            duration=itemView.findViewById(R.id.duration);
         //   image_profile=itemView.findViewById(R.id.profile_image);

        }
    }

}
