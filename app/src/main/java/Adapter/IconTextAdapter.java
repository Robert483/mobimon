package Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import Object.*;

/**
 * Created by Ryan L. Vu on 6/5/2016.
 */
public class IconTextAdapter extends BaseAdapter {

    private int      resource;
    private int      textViewResourceId;
    private int      imageViewResourceId;
    private ArrayList<Equipment> arrData;
    private Context context;

    public IconTextAdapter(Context context, int resource, int textViewResourceId, int imageViewResourceId, ArrayList<Equipment> arrData) {
        super();
        this.context             = context;
        this.arrData             = arrData;
        this.resource            = resource;
        this.textViewResourceId  = textViewResourceId;
        this.imageViewResourceId = imageViewResourceId;
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View           row      = inflater.inflate(this.resource, null);

        Equipment eq = arrData.get(position);

        TextView textView = (TextView)row.findViewById(this.textViewResourceId);
        textView.setText(eq.getName());

        ImageView imageView = (ImageView)row.findViewById(this.imageViewResourceId);
        eq.addImageToImageView(imageView,context);

        return row;
    }
}
