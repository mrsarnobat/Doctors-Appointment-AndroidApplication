package com.example.sarnobatvipul.mydocapp.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sarnobatvipul.mydocapp.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class docProfileFragment extends Fragment {


    private ImageView mProfileImage;
    private TextView mProfileName, mProfileStatus, mProfileFriendsCount;
    private Button mProfileSendReqBtn;

    private ProgressDialog mProgressDialog;

    private DatabaseReference mUsersDatabase,mFriendReqDatabase;
    private FirebaseUser mCurrent_user;

    private String mCurrent_state;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doc_profile, container, false);


        //final String user_id=getIntent.getStringExtra("user_id");

/*
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference mUsersDatabase= FirebaseDatabase.getInstance().getReference("DoctorData").child(firebaseUser.getUid());

        mFriendReqDatabase= FirebaseDatabase.getInstance().getReference().child("Friend_req");
        mCurrent_user= FirebaseAuth.getInstance().getCurrentUser();


        mProfileImage=(ImageView)view.findViewById(R.id.profile_image);
        mProfileName=(TextView)view.findViewById(R.id.profile_displayName);
        mProfileStatus=(TextView)view.findViewById(R.id.profile_status);
        mProfileFriendsCount=(TextView)view.findViewById(R.id.profile_totalFriend);
        mProfileSendReqBtn=(Button)view.findViewById(R.id.profile_send_req_btn);

        mCurrent_state="not_friends";
*/
        /*
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setTitle("Loading User Data");
        mProgressDialog.setMessage("Please wait while we load  the user data...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
         */
/*
        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String display_name=dataSnapshot.child("name").getValue().toString();
                String status=dataSnapshot.child("status").getValue().toString();
                String image=dataSnapshot.child("image").getValue().toString();

                mProfileName.setText(display_name);
                mProfileStatus.setText(status);

                // Picasso.with(DisplayProfileActivity.this).load(image).placeholder(R.drawable.doc_icon).into(mProfileImage);

                mProgressDialog.dismiss();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mProfileSendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrent_state.equals("not_friends")){
                    mFriendReqDatabase.child(mCurrent_user.getUid()).child(firebaseUser.getUid()).child("request_type")
                            .setValue("sent").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mFriendReqDatabase.child(firebaseUser.getUid()).child(mCurrent_user.getUid()).child("request_type")
                                        .setValue("received").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //Toast.makeText(DisplayProfileActivity.this,"Request Sent Successfully",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else {
                               // Toast.makeText(DisplayProfileActivity.this,"Failed Send Request.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


*/


        return view;
    }
}
