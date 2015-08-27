package com.pj.creditcardmanagement.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by PJ on 8/24/2015.
 */
public class InfoDialog {

    public static void show(Context context, String message, DialogInterface.OnClickListener onClick) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Info");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", onClick);
        alertDialog.show();
    }


}
