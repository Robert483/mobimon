package Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


import com.alarmnotification.mobimon.R;

import java.util.ArrayList;

import Adapter.BagGridViewAdapter;

/**
 * Created by SonPham on 6/5/2016.
 */
public class BagTabFragment extends Fragment
    implements GridView.OnItemClickListener
    , Button.OnClickListener
    , Animation.AnimationListener {
    GridView gridView;
    ArrayList prgmName;

    View deTailItem;
    TextView txtNameCurrItem;
    Button btnSale, btnCancel;

    Animation animFadein,animFadeout;

    public static String [] prgmNameList={"Áo giáp vàng","Áo giáp bạc","Nón vàng","Nón bạc","Kiếm vàng","Kiếm bạc","Nhẫn bạc"};
    public static int [] prgmImages={R.drawable.canada,R.drawable.china,R.drawable.russia
            ,R.drawable.iran,R.drawable.italy,R.drawable.japan,R.drawable.mexico};

    public static BagTabFragment newInstance() {
        //Bundle args = new Bundle();
        //args.putInt(ARG_PAGE, page);
        BagTabFragment fragment = new BagTabFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mPage = getArguments().getInt(ARG_PAGE);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.bag_tab, container, false);
        gridView=(GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new BagGridViewAdapter(view.getContext(), prgmNameList, prgmImages));
        gridView.setOnItemClickListener(this);

        deTailItem = view.findViewById(R.id.viewDetailItem);
        deTailItem.setVisibility(View.GONE);

        txtNameCurrItem = (TextView) deTailItem.findViewById(R.id.txtName);
        btnSale = (Button) deTailItem.findViewById(R.id.btnSale);
        btnCancel = (Button) deTailItem.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnSale.setOnClickListener(this);

        animFadein = AnimationUtils.loadAnimation(getContext(), R.anim.show_detail_item);
        animFadeout = AnimationUtils.loadAnimation(getContext(), R.anim.hide_detail_item);
        animFadein.setAnimationListener(this);
        animFadeout.setAnimationListener(this);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        txtNameCurrItem.setText(prgmNameList[position]);
        // start the animation

        deTailItem.startAnimation(animFadein);;


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnCancel:
                deTailItem.startAnimation(animFadeout);
                break;

            case R.id.btnSale:

                break;


        }

    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (animation == animFadein) {
            deTailItem.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == animFadeout) {
            deTailItem.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}