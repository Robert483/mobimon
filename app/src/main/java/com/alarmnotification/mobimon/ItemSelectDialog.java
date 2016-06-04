package com.alarmnotification.mobimon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Ryan L. Vu on 6/4/2016.
 */
public class ItemSelectDialog extends DialogFragment implements DialogInterface.OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select equipment:");
        builder.setPositiveButton("Choose", this);
        builder.setNegativeButton("Cancel", this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
        }
    }
}
