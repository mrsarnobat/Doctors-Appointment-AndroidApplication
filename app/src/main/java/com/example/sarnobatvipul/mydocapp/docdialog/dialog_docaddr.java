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

public class dialog_docaddr extends AppCompatDialogFragment {
    private EditText docaddr;
    private docaddrDialogListener docaddrlistener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_docaddr,null);
        builder.setView(view)
                .setTitle("Enter Doctor Address")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String addr=docaddr.getText().toString();
                        docaddrlistener.applydocaddrText(addr);
                    }
                });

        docaddr=view.findViewById(R.id.doc_addr_dialog);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            docaddrlistener=(docaddrDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Enter again doctor address");
        }
    }

    public interface docaddrDialogListener{
        void applydocaddrText(String addr);
    }
}
