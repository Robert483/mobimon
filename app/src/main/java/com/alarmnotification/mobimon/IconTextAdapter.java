package com.alarmnotification.mobimon;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ryan L. Vu on 6/5/2016.
 */
public class IconTextAdapter extends ArrayAdapter<String> {

    private int      resource;
    private int      textViewResourceId;
    private int      imageViewResourceId;
    private int[]    imageRes;
    private String[] info;

    public IconTextAdapter(Context context, int resource, int textViewResourceId, int imageViewResourceId, String[] info, int[] imageRes) {
        super(context, resource, textViewResourceId, info);

        this.info                = info;
        this.resource            = resource;
        this.imageRes            = imageRes;
        this.textViewResourceId  = textViewResourceId;
        this.imageViewResourceId = imageViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context        context  = this.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View           row      = inflater.inflate(this.resource, null);

        TextView textView = (TextView)row.findViewById(this.textViewResourceId);
        textView.setText(info[position]);

        ImageView imageView = (ImageView)row.findViewById(this.imageViewResourceId);
        imageView.setImageDrawable(ContextCompat.getDrawable(context, imageRes[position]));

        return row;
    }
}
