package com.alarmnotification.mobimon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;

import Object.*;

public class PetActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageButton ibmHead, ibmBody, ibmWing, ibmFoot;
    DBHelper dbHelper;
    ArrayList<Equipment> arrData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        initData();

        initLayout();
    }

    private void initLayout() {
        Equipment equipment = null;

        ibmBody = (ImageButton)this.findViewById(R.id.bodySelect);
        ibmBody.setOnClickListener(this);
        equipment = dbHelper.getCurrentBody();
        equipment.addImageToImageView(ibmBody, this);

        ibmHead = (ImageButton)this.findViewById(R.id.headSelect);
        ibmHead.setOnClickListener(this);
        equipment = dbHelper.getCurrentHead();
        equipment.addImageToImageView(ibmHead, this);

        ibmWing = (ImageButton)this.findViewById(R.id.wingSelect);
        ibmWing.setOnClickListener(this);
        equipment = dbHelper.getCurrentWing();
        equipment.addImageToImageView(ibmWing, this);

        ibmFoot = (ImageButton)this.findViewById(R.id.footSelect);
        ibmFoot.setOnClickListener(this);
        equipment = dbHelper.getCurrentFoot();
        equipment.addImageToImageView(ibmFoot, this);

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
