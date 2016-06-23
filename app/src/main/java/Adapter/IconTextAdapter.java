package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Object.Item;

/**
 * Created by Ryan L. Vu on 6/5/2016.
 */
public class IconTextAdapter<T extends Item> extends ArrayAdapter<T> {
    private int resource;
    private int txtVRId;
    private int imgVRId;

    public IconTextAdapter(Context context, int resource, int textViewResourceId, int imageViewResourceId, List<T> items) {
        super(context, resource, textViewResourceId, items);
        this.resource = resource;
        this.txtVRId  = textViewResourceId;
        this.imgVRId  = imageViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View           row      = inflater.inflate(this.resource, null);
        Item           item      = this.getItem(position);

        TextView textView = (TextView)row.findViewById(this.txtVRId);
        textView.setText(item.getName());

        ImageView imageView = (ImageView)row.findViewById(this.imgVRId);
        imageView.setImageBitmap(item.getImage());

        return row;
    }
}
