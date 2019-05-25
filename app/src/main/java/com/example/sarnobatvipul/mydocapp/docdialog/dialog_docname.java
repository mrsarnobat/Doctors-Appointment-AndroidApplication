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

public class dialog_docname extends AppCompatDialogFragment {
    private EditText et_docname;
    private docnameDialogListener docnlistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_docname,null);
        builder.setView(view)
                .setTitle("Enter Doctor Name")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String docname=et_docname.getText().toString();
                        docnlistener.applydocnameText(docname);
                    }
                });

        et_docname=view.findViewById(R.id.doc_name_dialog);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            docnlistener=(docnameDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Enter again doctor name");
        }
    }

    public interface docnameDialogListener{
        void applydocnameText(String docname);
    }
}
