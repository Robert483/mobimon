package Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import Object.Equipment;

/**
 * Created by Ryan L. Vu on 6/5/2016.
 */
public class IconTextAdapter extends ArrayAdapter<Equipment> {
    private int         resource;
    private int         txtVRId;
    private int         imgVRId;

    public IconTextAdapter(Context context, int resource, int textViewResourceId, int imageViewResourceId, Equipment[] equipments) {
        super(context, resource, textViewResourceId, equipments);
        this.resource = resource;
        this.txtVRId  = textViewResourceId;
        this.imgVRId  = imageViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View           row      = inflater.inflate(this.resource, null);
        Equipment      eqm      = this.getItem(position);

        TextView textView = (TextView)row.findViewById(this.txtVRId);
        textView.setText(eqm.getName());

        ImageView imageView = (ImageView)row.findViewById(this.imgVRId);
        imageView.setImageBitmap(eqm.getImage());

        return row;
    }
}
