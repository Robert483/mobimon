package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alarmnotification.mobimon.R;


/**
 * Created by SonPham on 6/5/2016.
 */
public class BagGridViewAdapter extends BaseAdapter{

    String [] result;
    Context context;
    int [] imageId;
    public BagGridViewAdapter(Context context, String[] prgmNameList, int[] prgmImages) {

        result=prgmNameList;
        this.context=context;
        imageId=prgmImages;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = inflater.inflate(R.layout.bag_gridview_row, null);

        if((position/3)%2 == 1){
            rowView.setBackgroundColor(Color.WHITE);
        }


        holder.tv=(TextView) rowView.findViewById(R.id.txtName);
        holder.img=(ImageView) rowView.findViewById(R.id.imgIcon);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);

        return rowView;
    }

}