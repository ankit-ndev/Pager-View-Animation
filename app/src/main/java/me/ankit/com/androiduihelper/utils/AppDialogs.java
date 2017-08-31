package me.ankit.com.androiduihelper.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import me.ankit.com.androiduihelper.R;

/**
 * Developer: ankit
 * Dated: 30/08/17.
 */

public class AppDialogs {


    public static Dialog dialogAutomaticPageChange(Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_automatic_page_change);
        Button btnYes, btnNo;
        btnYes = (Button) dialog.findViewById(R.id.btn_yes);
        btnNo = (Button) dialog.findViewById(R.id.btn_no);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dialog.show();
        return dialog;
    }
}