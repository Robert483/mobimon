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
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import Adapter.BagGridViewAdapter;
import Document.Config;
import Object.*;


/**
 * Created by SonPham on 6/5/2016.
 */
public class BagTabFragment extends Fragment
        implements GridView.OnItemClickListener
        , Button.OnClickListener
        , Animation.AnimationListener {
    GridView gridView;


    View deTailItem;
    TextView txtNameCurrItem, txtDetailCurrItem;
    Button btnSale, btnCancel;

    Animation animFadein,animFadeout;
    BagGridViewAdapter adapter;


    ArrayList<Item> arrData;

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

        Firebase.setAndroidContext(getContext());
        final Firebase ref = new Firebase(Config.FIREBASE_URL);

        //Value event listener for realtime data update
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                DataSnapshot currSnapshot =  snapshot.child("Food");
                for (DataSnapshot postSnapshot : currSnapshot.getChildren()) {
                    Food item = new Food(postSnapshot);
                    arrData.add(item);
                }
                currSnapshot =  snapshot.child("Body");
                for (DataSnapshot postSnapshot : currSnapshot.getChildren()) {
                    Equipment item = new Equipment(postSnapshot);
                    //Getting the data from snapshot
                    arrData.add(item);
                }
                currSnapshot =  snapshot.child("Wing");
                for (DataSnapshot postSnapshot : currSnapshot.getChildren()) {
                    Equipment item = new Equipment(postSnapshot);
                    //Getting the data from snapshot
                    arrData.add(item);
                }
                currSnapshot =  snapshot.child("Foot");
                for (DataSnapshot postSnapshot : currSnapshot.getChildren()) {
                    Equipment item = new Equipment(postSnapshot);
                    //Getting the data from snapshot
                    arrData.add(item);
                }
                currSnapshot =  snapshot.child("Head");
                for (DataSnapshot postSnapshot : currSnapshot.getChildren()) {
                    Equipment item = new Equipment(postSnapshot);
                    //Getting the data from snapshot
                    arrData.add(item);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        txtNameCurrItem.setText(arrData.get(position).getName());
        txtDetailCurrItem.setText(arrData.get(position).getDetail());

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