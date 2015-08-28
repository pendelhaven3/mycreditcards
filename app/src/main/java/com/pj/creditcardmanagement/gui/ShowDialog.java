package com.pj.creditcardmanagement.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by PJ on 8/24/2015.
 */
public class ShowDialog {

    private static final DialogInterface.OnClickListener DUMMY_ONCLICK_LISTENER =
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            };

    public static void info(Context context, String message) {
        info(context, message, DUMMY_ONCLICK_LISTENER);
    }

    public static void info(Context context, String message, DialogInterface.OnClickListener onClick) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Info");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", onClick);
        alertDialog.show();
    }

    public static void error(Context context, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", DUMMY_ONCLICK_LISTENER);
        alertDialog.show();
    }

}
