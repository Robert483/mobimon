package Adapter;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alarmnotification.mobimon.R;

/**
 * Created by Thai Son on 04/06/2016.
 */
public class ImageAdapter extends BaseAdapter
{
    private ArrayList<String> listNames;
    private ArrayList<Integer> listImage;
    private Activity activity;

    public ImageAdapter(Activity activity, ArrayList<String> listNames, ArrayList<Integer> listImage) {
        super();
        this.listNames = listNames;
        this.listImage = listImage;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listNames.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return listNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return listImage.get(position);
    }

    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView txtViewTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.gridview_row, null);

            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView_TS);
            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView_TS);

            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }

        view.txtViewTitle.setText(listNames.get(position));
        view.imgViewFlag.setImageResource(listImage.get(position));

        return convertView;
    }
}
