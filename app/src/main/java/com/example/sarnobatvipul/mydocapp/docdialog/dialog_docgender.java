package com.example.sarnobatvipul.mydocapp.docdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Spinner;

import com.example.sarnobatvipul.mydocapp.R;


/**
 * Created by sarno on 15-02-2019.
 */

public class dialog_docgender extends AppCompatDialogFragment {
    private Spinner genderSp;
    private String selection;
    private docgenderDialogListener docglistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        final String[] docgenders=getActivity().getResources().getStringArray(R.array.ptgenders);

        builder.setTitle("Select Your Gender");
        builder.setSingleChoiceItems(R.array.ptgenders, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selection=docgenders[which];
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String docgender=selection.toString();
                docglistener.applydocgenderText(docgender);

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
            docglistener=(docgenderDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Select again gender");
        }
    }

    public interface docgenderDialogListener{
        void applydocgenderText(String docgender);
    }
 }
