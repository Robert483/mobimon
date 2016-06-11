package com.alarmnotification.mobimon;

import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

/**
 * Created by Thai Son on 04/06/2016.
 */
public class Feeding extends AppCompatActivity {
    private ImageAdapter mAdapter;
    private ArrayList<String> listItem;
    private ArrayList<Integer> listFlag;

    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_art);
        setContentView(R.layout.activity_feeding);
        prepareList();

        // prepared arraylist and passed it to the Adapter class
        mAdapter = new ImageAdapter(this,listItem, listFlag);

        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.foodGrid);
        gridView.setAdapter(mAdapter);

        // Implement On Item click listener
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                Builder builder = new AlertDialog.Builder(Feeding.this);
                AlertDialog dialog = builder.create();
                dialog.setTitle(mAdapter.getItem(position));
                dialog.setIcon((int) mAdapter.getItemId(position));
                dialog.setMessage("Thông tin chi tiết về Item");
                dialog.setButton("Bán", listenerAccept);
                dialog.setButton2("Quay lại", listenerDoesNotAccept);
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    DialogInterface.OnClickListener listenerAccept = new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int which) {
            //Toast.makeText(SplashArt.this, "Great! Welcome.", Toast.LENGTH_SHORT).show();
            Toast.makeText(Feeding.this, "Đã bán!", Toast.LENGTH_SHORT).show();
        }
    };

    DialogInterface.OnClickListener listenerDoesNotAccept = new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int which) {
            //Toast.makeText(SplashArt.this, "Đã bán!", Toast.LENGTH_SHORT).show();
        }
    };

    public void prepareList()
    {
        listItem = new ArrayList<String>();

        listItem.add("india");
        listItem.add("Brazil");
        listItem.add("Canada");
        listItem.add("China");
        listItem.add("France");
        listItem.add("Germany");
        listItem.add("Iran");
        listItem.add("Italy");
        listItem.add("Japan");
        listItem.add("Korea");
        listItem.add("Mexico");
        listItem.add("Netherlands");
        listItem.add("Portugal");
        listItem.add("Russia");
        listItem.add("Saudi Arabia");
        listItem.add("Spain");
        listItem.add("Turkey");
        listItem.add("United Kingdom");
        listItem.add("United States");

        listFlag = new ArrayList<Integer>();
        listFlag.add(R.drawable.india);
        listFlag.add(R.drawable.brazil);
        listFlag.add(R.drawable.canada);
        listFlag.add(R.drawable.china);
        listFlag.add(R.drawable.france);
        listFlag.add(R.drawable.germany);
        listFlag.add(R.drawable.iran);
        listFlag.add(R.drawable.italy);
        listFlag.add(R.drawable.japan);
        listFlag.add(R.drawable.korea);
        listFlag.add(R.drawable.mexico);
        listFlag.add(R.drawable.netherlands);
        listFlag.add(R.drawable.portugal);
        listFlag.add(R.drawable.russia);
        listFlag.add(R.drawable.saudi_arabia);
        listFlag.add(R.drawable.spain);
        listFlag.add(R.drawable.turkey);
        listFlag.add(R.drawable.united_kingdom);
        listFlag.add(R.drawable.united_states);
    }
}
