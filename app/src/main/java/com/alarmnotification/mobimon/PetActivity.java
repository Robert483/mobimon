package com.alarmnotification.mobimon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

import Object.*;

public class PetActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton ibmHeadSelected, ibmBodySelected, ibmWingSelected, ibmFootSelected;
    ImageView imvHead, imvBody, imvWing, imvFoot;
    ArrayList<Item> arrData;
    GridView gridView;
    Item currItemSelected;
    DBHelper dbHelper;
    Equipment eqHead, eqWing, eqBody, eqFoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        initData();

        initLayout();
    }

    private void initLayout() {
        //init image button selected
        ibmBodySelected = (ImageButton)this.findViewById(R.id.bodySelect);
        ibmBodySelected.setOnClickListener(this);

        ibmHeadSelected = (ImageButton)this.findViewById(R.id.headSelect);
        ibmHeadSelected.setOnClickListener(this);

        ibmWingSelected = (ImageButton)this.findViewById(R.id.wingSelect);
        ibmWingSelected.setOnClickListener(this);

        ibmFootSelected = (ImageButton)this.findViewById(R.id.footSelect);
        ibmFootSelected.setOnClickListener(this);

        imvBody = (ImageView) findViewById(R.id.body_pet);
        imvWing = (ImageView) findViewById(R.id.wing_pet);
        imvHead = (ImageView) findViewById(R.id.head_pet);
        imvFoot = (ImageView) findViewById(R.id.foot_pet);




        eqBody = dbHelper.getCurrentBody();
        eqBody.addImageToImageView(ibmBodySelected, this);
        eqBody.addLargeImageToImageView(imvBody, this);

        eqHead = dbHelper.getCurrentHead();
        eqHead.addImageToImageView(ibmHeadSelected, this);
        eqHead.addLargeImageToImageView(imvHead, this);

        eqWing = dbHelper.getCurrentWing();
        eqWing.addImageToImageView(ibmWingSelected, this);
        eqWing.addLargeImageToImageView(imvWing, this);

        eqFoot = dbHelper.getCurrentFoot();
        eqFoot.addImageToImageView(ibmFootSelected, this);
        eqFoot.addLargeImageToImageView(imvFoot, this);
    }

    private void initData() {
        if(dbHelper==null) {
            dbHelper = new DBHelper(this);
            try {
                dbHelper.createDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.headSelect:
                openSelectEqmDialog("head");
                break;
            case R.id.wingSelect:
                openSelectEqmDialog("wing");
                break;
            case R.id.bodySelect:
                openSelectEqmDialog("body");
                break;
            case R.id.footSelect:
                openSelectEqmDialog("foot");
                break;
        }
    }

    private void openSelectEqmDialog(String type) {
        ItemSelectDialog.newInstance(type).show(this.getSupportFragmentManager(), "Select Equipment");

    }


}
