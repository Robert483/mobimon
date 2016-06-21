package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alarmnotification.mobimon.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;
import Object.*;

/**
 * Created by SonPham on 6/5/2016.
 */
public class BagGridViewAdapter extends BaseAdapter{

    ArrayList<Item> arrData;
    Context context;

    public BagGridViewAdapter(Context context, ArrayList<Item> arrData) {

        this.arrData=arrData;
        this.context=context;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrData.size();
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

        Item item = arrData.get(position);
        holder.tv.setText(item.getName());

        if(item.getImage() != null){
            holder.img.setImageBitmap(item.getImage());
            //Log.d("Son", "123312123");
        }
        else {
            UrlImageViewHelper.setUrlDrawable(holder.img, arrData.get(position).getLinkimage(), R.mipmap.ic_launcher);
        }


        return rowView;
    }

}