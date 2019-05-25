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

public class dialog_ptemailid extends AppCompatDialogFragment {
    private EditText et_ptemailid;
    private ptemailidDialogListener ptelistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_ptemailid,null);
        builder.setView(view)
                .setTitle("Enter Pantient E-mail ID")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String ptemailid=et_ptemailid.getText().toString();
                        ptelistener.applyptemailidText(ptemailid);
                    }
                });

        et_ptemailid=view.findViewById(R.id.pt_emailid_dialog);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            ptelistener=(ptemailidDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Enter again patient email id");
        }
    }

    public interface ptemailidDialogListener{
        void applyptemailidText(String ptemailid);
    }
}
