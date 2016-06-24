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
import android.widget.Toast;


import com.alarmnotification.mobimon.R;

import java.io.IOException;
import java.util.ArrayList;

import Adapter.BagGridViewAdapter;
import Object.*;


/**
 * Created by SonPham on 6/5/2016.
 */
public class BagTabFragment extends Fragment
        implements GridView.OnItemClickListener
        , Button.OnClickListener
        , Animation.AnimationListener{
    GridView gridView;

    View deTailItem;
    TextView txtNameCurrItem, txtDetailCurrItem;
    Button btnSale, btnCancel;

    Animation animFadein,animFadeout;
    BagGridViewAdapter adapter;
    ArrayList<Item> arrData;
    Item currItem;

    DBHelper dbHelper;


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
        View view = inflater.inflate(R.layout.bag_tab, container, false);

        initData();

        initLayout(view);

        return view;
    }

    private void initLayout(View view) {
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        gridView=(GridView) view.findViewById(R.id.gridView);

        adapter = new BagGridViewAdapter(view.getContext(), arrData);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

        deTailItem = view.findViewById(R.id.viewDetailItem);
        deTailItem.setVisibility(View.GONE);

        txtNameCurrItem = (TextView) deTailItem.findViewById(R.id.txtName);
        txtDetailCurrItem = (TextView) deTailItem.findViewById(R.id.txtDetail);
        btnSale = (Button) deTailItem.findViewById(R.id.btnSale);
        btnCancel = (Button) deTailItem.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnSale.setOnClickListener(this);

        animFadein = AnimationUtils.loadAnimation(getContext(), R.anim.show_detail_item);
        animFadeout = AnimationUtils.loadAnimation(getContext(), R.anim.hide_detail_item);
        animFadein.setAnimationListener(this);
        animFadeout.setAnimationListener(this);


    }

    private void initData() {
        arrData = new ArrayList<Item>();
        if(dbHelper==null) {
            dbHelper = new DBHelper(getContext());
            try {
                dbHelper.createDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        arrData.addAll(dbHelper.getAllOwnedFood());
        arrData.addAll(dbHelper.getAllOwnedEquipment());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currItem = arrData.get(position);
        txtNameCurrItem.setText(currItem.getName());
        txtDetailCurrItem.setText(currItem.getDetail());

        deTailItem.setVisibility(View.VISIBLE);
        deTailItem.startAnimation(animFadein);



    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnCancel:
                deTailItem.startAnimation(animFadeout);
                break;

            case R.id.btnSale:
                dbHelper.deleteItem(currItem);
                arrData.remove(currItem);
                adapter.notifyDataSetChanged();
                deTailItem.setVisibility(View.GONE);
                deTailItem.startAnimation(animFadeout);
                Toast.makeText(getContext(), "Bán thành công", Toast.LENGTH_LONG).show();
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

    public void updateGridView() {

        if (dbHelper==null) {
            return;
        }
        arrData.clear();
        arrData.addAll(dbHelper.getAllOwnedFood());
        arrData.addAll(dbHelper.getAllOwnedEquipment());
        adapter.notifyDataSetChanged();
    }

}