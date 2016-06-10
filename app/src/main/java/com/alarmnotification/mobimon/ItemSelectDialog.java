package com.alarmnotification.mobimon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Ryan L. Vu on 6/4/2016.
 */
public class ItemSelectDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public static ItemSelectDialog newInstance(int listViewResource, int listViewResourceId, int rowResource, int textViewResourceId, int imageViewResourceId, String[] info, int[] imageRes) {
        Bundle args = new Bundle();
        args.putInt("lvR", listViewResource);
        args.putInt("lvRId", listViewResourceId);
        args.putInt("rR", rowResource);
        args.putInt("tvRId", textViewResourceId);
        args.putInt("imgRId", imageViewResourceId);
        args.putStringArray("info", info);
        args.putIntArray("imageRes", imageRes);

        ItemSelectDialog dialog = new ItemSelectDialog();
        dialog.setArguments(args);

        return dialog;
    }

    public static ItemSelectDialog newInstance(String[] info, int[] imageRes) {
        Bundle args = new Bundle();
        args.putStringArray("info", info);
        args.putIntArray("imageRes", imageRes);

        ItemSelectDialog dialog = new ItemSelectDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = this.getArguments();

        AlertDialog.Builder builder  = new AlertDialog.Builder(this.getActivity());
        LayoutInflater      inflater = getActivity().getLayoutInflater();
        View                view     = inflater.inflate(args.getInt("lvR", R.layout.listview_holder), null);

        ListView listView = (ListView)view.findViewById(args.getInt("lvRId", R.id.listViewHolder));
        listView.setSelector(android.R.color.darker_gray);
        listView.setAdapter(new IconTextAdapter(getContext(), args.getInt("rR", R.layout.icon_text_row), args.getInt("tvRId", R.id.textViewRow), args.getInt("imgRId", R.id.imageViewRow), args.getStringArray("info"), args.getIntArray("imageRes")));

        builder.setView(view);
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
