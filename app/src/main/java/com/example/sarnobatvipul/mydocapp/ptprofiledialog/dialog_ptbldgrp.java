package com.example.sarnobatvipul.mydocapp.ptprofiledialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.sarnobatvipul.mydocapp.R;


/**
 * Created by sarno on 15-02-2019.
 */

public class dialog_ptbldgrp extends AppCompatDialogFragment {
    private String selection;
    private ptbldgrpDialogListener ptbldgrplistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        final String[] ptbldgrp=getActivity().getResources().getStringArray(R.array.ptblooodgrp);

        builder.setTitle("Select Your Blood Group");
        builder.setSingleChoiceItems(R.array.ptblooodgrp, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selection=ptbldgrp[which];
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ptbldgrp=selection.toString();
                ptbldgrplistener.applyptbldgrpText(ptbldgrp);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            ptbldgrplistener=(ptbldgrpDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Select again blood group");
        }
    }

    public interface ptbldgrpDialogListener{
        void applyptbldgrpText(String ptbldgrp);
    }
 }
