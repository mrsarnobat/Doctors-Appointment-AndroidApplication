package com.example.sarnobatvipul.mydocapp.docdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.sarnobatvipul.mydocapp.R;


/**
 * Created by sarno on 15-02-2019.
 */

public class dialog_docemailid extends AppCompatDialogFragment {
    private EditText et_docemailid;
    private docemailidDialogListener docelistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_docemailid,null);
        builder.setView(view)
                .setTitle("Enter Doctor E-mail ID")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String docemailid=et_docemailid.getText().toString();
                        docelistener.applydocemailidText(docemailid);
                    }
                });

        et_docemailid=view.findViewById(R.id.doc_emailid_dialog);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            docelistener=(docemailidDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Enter again doctor email id");
        }
    }

    public interface docemailidDialogListener{
        void applydocemailidText(String docemailid);
    }
}
