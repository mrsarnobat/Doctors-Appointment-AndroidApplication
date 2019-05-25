package com.example.sarnobatvipul.mydocapp.ptprofiledialog;

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

public class dialog_ptcontact extends AppCompatDialogFragment {
    private EditText et_ptcontact;
    private ptcontactDialogListener ptclistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_ptcontact,null);
        builder.setView(view)
                .setTitle("Enter Pantient Contact Number")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String ptcontact=et_ptcontact.getText().toString();
                        ptclistener.applyptcontactText(ptcontact);
                    }
                });

        et_ptcontact=view.findViewById(R.id.pt_contact_dialog);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            ptclistener=(ptcontactDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Enter again patient Contact");
        }
    }

    public interface ptcontactDialogListener{
        void applyptcontactText(String ptcontact);
    }
}
