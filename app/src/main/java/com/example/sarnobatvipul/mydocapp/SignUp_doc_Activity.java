package com.example.sarnobatvipul.mydocapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.santalu.maskedittext.MaskEditText;

import java.util.HashMap;

public class SignUp_doc_Activity extends AppCompatActivity {

    TextView signinhere;
    Button signupbtn;
    MaskEditText name,contact,email,password;
    ProgressBar progressBar;

    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doc);

        name=(MaskEditText)findViewById(R.id.doc_name_et);
        email=(MaskEditText)findViewById(R.id.doc_emailid_et);
        contact=(MaskEditText)findViewById(R.id.doc_contact_et);
        password=(MaskEditText)findViewById(R.id.doc_pwd_et);
        signupbtn=(Button)findViewById(R.id.btn_signup_doc);
        signinhere=(TextView)findViewById(R.id.sign_in_here);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);

        auth=FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_name=name.getText().toString();
                String txt_email=email.getText().toString();
             //   String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String txt_contact=contact.getText().toString();
                String txt_password=password.getText().toString();

                if (txt_name.isEmpty()){
                    name.setError("required");
                    name.requestFocus();
                    return;
                }
                if (txt_email.isEmpty() )
                {
                    email.setError("vaild EmailID");
                    email.requestFocus();
                    return;
                }
                if (txt_contact.isEmpty() || txt_contact.length()>10 || txt_contact.length()<10)
                {
                    contact.setError("contact number must be at least 10 digits");
                    contact.requestFocus();
                    return;
                }
                if (txt_password.isEmpty() || txt_contact.length()<6)
                {
                    password.setError("password must be at least 6 character");
                    password.requestFocus();
                    return;
                }

                else
                {
                    progressBar.setVisibility(view.VISIBLE);
                    register(txt_name,txt_email,txt_contact,txt_password);
                }

            }
        });

        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignUp_doc_Activity.this,SignIn_Doc_Activity.class));
            }
        });



    }


    private void register(final String name, final String email, final String contact, String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser=auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid=firebaseUser.getUid();

                            reference=FirebaseDatabase.getInstance().getReference("DoctorData").child(userid);
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("name",name);
                            hashMap.put("emailid",email);
                            hashMap.put("contact",contact);
                            hashMap.put("address","");
                            hashMap.put("gender","");
                            hashMap.put("dob","");
                            hashMap.put("bloodgroup","");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent=new Intent(SignUp_doc_Activity.this,SignIn_Doc_Activity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(SignUp_doc_Activity.this,"You can't with this email and password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
