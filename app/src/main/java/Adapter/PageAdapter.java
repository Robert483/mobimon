package Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alarmnotification.mobimon.R;
import Fragment.*;

/**
 * Created by SonPham on 6/5/2016.
 */
public class PageAdapter extends FragmentPagerAdapter {

    //integer to count number of tabs
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Túi đồ", "Cửa hàng" };
    private Context context;
    private int[] imageResId = {R.drawable.bag, R.drawable.store};

    //Constructor to the class
    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;

    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                BagTabFragment bagTabFragment = BagTabFragment.newInstance();
                return bagTabFragment;
            case 1:
                StoreTabFragment storeTabFragment = StoreTabFragment.newInstance();
                return storeTabFragment;

        }
        return null;
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) v.findViewById(R.id.imgIconPage);
        img.setImageResource(imageResId[position]);
        return v;
    }


}
