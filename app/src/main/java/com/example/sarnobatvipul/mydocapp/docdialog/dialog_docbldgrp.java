package com.example.sarnobatvipul.mydocapp.docdialog;

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

public class dialog_docbldgrp extends AppCompatDialogFragment {
    private String selection;
    private docbldgrpDialogListener docbldgrplistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        final String[] docbldgrp=getActivity().getResources().getStringArray(R.array.ptblooodgrp);

        builder.setTitle("Select Your Blood Group");
        builder.setSingleChoiceItems(R.array.ptblooodgrp, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selection=docbldgrp[which];
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String docbldgrp=selection.toString();
                docbldgrplistener.applydocbldgrpText(docbldgrp);

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
            docbldgrplistener=(docbldgrpDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Select again blood group");
        }
    }

    public interface docbldgrpDialogListener{
        void applydocbldgrpText(String docbldgrp);
    }
 }
