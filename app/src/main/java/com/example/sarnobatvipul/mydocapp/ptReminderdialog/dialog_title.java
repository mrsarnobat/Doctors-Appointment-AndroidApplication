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

public class dialog_title extends AppCompatDialogFragment {
    private EditText mtitle;
    private titleDialogListener tlistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_title,null);
        builder.setView(view)
                .setTitle("Enter Title Name")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String title=mtitle.getText().toString();
                        tlistener.applytitleText(title);
                    }
                });

        mtitle=view.findViewById(R.id.title);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            tlistener=(titleDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Enter again");
        }
    }

    public interface titleDialogListener{
        void applytitleText(String title);
    }
}
