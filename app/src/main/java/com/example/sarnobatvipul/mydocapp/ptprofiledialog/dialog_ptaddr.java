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

public class dialog_ptaddr extends AppCompatDialogFragment {
    private EditText ptaddr;
    private ptaddrDialogListener ptaddrlistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_ptaddr,null);
        builder.setView(view)
                .setTitle("Enter Pantient Address")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String addr=ptaddr.getText().toString();
                        ptaddrlistener.applyptaddrText(addr);
                    }
                });

        ptaddr=view.findViewById(R.id.pt_addr_dialog);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            ptaddrlistener=(ptaddrDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Enter again patient address");
        }
    }

    public interface ptaddrDialogListener{
        void applyptaddrText(String addr);
    }
}
