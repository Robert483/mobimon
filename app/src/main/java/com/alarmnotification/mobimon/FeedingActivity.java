package com.alarmnotification.mobimon;

import java.io.IOException;
import java.util.ArrayList;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

import Adapter.BagGridViewAdapter;
import Adapter.ImageAdapter;
import Object.*;

/**
 * Created by Thai Son on 04/06/2016.
 */
public class FeedingActivity extends AppCompatActivity implements OnItemClickListener {
    private BagGridViewAdapter adapter;
    ArrayList<Item> arrData;
    GridView gridView;
    Food currItemSelected;
    DBHelper dbHelper;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_art);
        setContentView(R.layout.activity_feeding);

        initData();

        initLayout();

    }

    private void initLayout() {
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        gridView=(GridView) findViewById(R.id.gridView);

        adapter = new BagGridViewAdapter(this, arrData);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.hpBar_feeding);
        progressBar.setProgress(100);

    }

    private void initData() {
        arrData = new ArrayList<Item>();
        if(dbHelper==null) {
            dbHelper = new DBHelper(this);
            try {
                dbHelper.createDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        arrData.addAll(dbHelper.getAllOwnedFood());
    }




    DialogInterface.OnClickListener listenerAccept = new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(FeedingActivity.this, "Fed!", Toast.LENGTH_SHORT).show();
            int currProgress =  progressBar.getProgress() + currItemSelected.getHp();
            if(currProgress<0)
                currProgress = 0;
            else  if (currProgress>100)
                currProgress = 100;
            progressBar.setProgress(currProgress);
        }
    };

    DialogInterface.OnClickListener listenerDoesNotAccept = new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int which) {

        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currItemSelected = (Food) arrData.get(position);
        Builder builder = new AlertDialog.Builder(FeedingActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle(currItemSelected.getName());
        Drawable d = new BitmapDrawable(getResources(), currItemSelected.getBitmapImage(this));
        dialog.setIcon(d);
        dialog.setMessage("Item information");
        dialog.setButton("Feed", listenerAccept);
        dialog.setButton2("Cancel", listenerDoesNotAccept);
        dialog.show();
    }
}
