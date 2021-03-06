package com.example.sarnobatvipul.mydocapp.ptReminderdialog;

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

public class dialog_timing extends AppCompatDialogFragment {
    private EditText mtiming;
    private timingDialogListener timinglistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_timing,null);
        builder.setView(view)
                .setTitle("Enter Timing")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String timing=mtiming.getText().toString();
                        timinglistener.applytimingText(timing);
                    }
                });

        mtiming=view.findViewById(R.id.timing);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            timinglistener=(timingDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Enter again");
        }
    }

    public interface timingDialogListener{
        void applytimingText(String timing);
    }
}
