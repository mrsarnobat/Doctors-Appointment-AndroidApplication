package com.example.sarnobatvipul.mydocapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.santalu.maskedittext.MaskEditText;

public class SignIn_Pt_Activity extends AppCompatActivity {

    MaskEditText email,password;
    TextView signuphere;
    Button signinbtn;
    ProgressBar progressBar;


  //  FirebaseAuth auth;
 //   DatabaseReference reference;
    FirebaseUser user;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in__pt);

        email=(MaskEditText)findViewById(R.id.pt_contact_et);
        password=(MaskEditText)findViewById(R.id.pt_pwd_et);
        signinbtn=(Button)findViewById(R.id.btn_signin_pt);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);

        mAuth= FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        mDatabase= FirebaseDatabase.getInstance().getReference();




        signin();


        signuphere=(TextView)findViewById(R.id.sign_up_here);
        signuphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn_Pt_Activity.this,SignUp_pt_Activity.class));
            }
        });
    }

    private void signin()
    {
        try
        {
            signinbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    String e=email.getText().toString();
                    String p=password.getText().toString();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    AwesomeValidation mAwesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
                    mAwesomeValidation.addValidation(SignIn_Pt_Activity.this,R.id.pt_contact_et, Patterns.EMAIL_ADDRESS,R.string.enteremailid);

                    if (e.isEmpty())
                {
                    email.setError("required");
                    email.requestFocus();
                    return;
                }

                if (p.length()<6 || p.isEmpty())
                {
                    password.setError("enter valid password");
                    password.requestFocus();
                    return;
                }


                    final String uid=user.getUid();

                    progressBar.setVisibility(view.VISIBLE);

                    mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent i=new Intent(SignIn_Pt_Activity.this,ptHomePage_Activity.class);

                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                              //  i.putExtra("id",uid);
                                startActivity(i);
                                finish();

                            }
                            else
                            {
                                progressBar.setVisibility(view.GONE);
                                Toast.makeText(SignIn_Pt_Activity.this, "Invalid user!..Please Create your account first", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            });

        }

        catch (Exception e)
        {
            Toast.makeText(SignIn_Pt_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }



    }

}
