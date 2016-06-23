package Adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import Object.Food;

import com.alarmnotification.mobimon.R;

/**
 * Created by Thai Son on 04/06/2016.
 */
public class ImageAdapter extends ArrayAdapter<Food> {
    public ImageAdapter(Context context, Food[] foods) {
        super(context, R.layout.gridview_row, foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ImageView imgViewFlag;
        TextView txtViewTitle;
        Food food;
        LayoutInflater inflator = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView==null)
        {
            convertView = inflator.inflate(R.layout.gridview_row, null);
            food = getItem(position);
            convertView.setTag(food);
        }
        else
        {
            food = (Food) convertView.getTag();
        }

        txtViewTitle = (TextView)convertView.findViewById(R.id.textView_TS);
        imgViewFlag = (ImageView)convertView.findViewById(R.id.imageView_TS);

        txtViewTitle.setText(food.getName());
        imgViewFlag.setImageBitmap(food.getImage());

        return convertView;
    }
}
