package com.alarmnotification.mobimon;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import Adapter.IconTextAdapter;
import Object.Equipment;
import Object.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class PetActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener, AdapterView.OnItemClickListener {
    private static final int NO_EQM = -1;
    private static final int HEAD_EQM = 0;
    private static final int BODY_EQM = 1;
    private static final int FOOT_EQM = 2;
    private static final int WING_EQM = 3;

    private HashMap<Integer, ImageButton> imgB_selectingParts;
    private HashMap<Integer, ImageView> imgV_PetParts;
    private HashMap<Integer, Equipment> petParts;
    private TextView petInfo;
    private ListView listV_selectingParts;
    private int selectedPartId;
    private Equipment selectedPart;
    private ArrayList<Equipment> selectingParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        this.selectingParts = null;
        this.selectedPartId = PetActivity.NO_EQM;
        this.selectedPart = null;
        this.petInfo = (TextView)this.findViewById(R.id.petInfo);

        Context context = getApplicationContext();
        DBHelper dbHelper = new DBHelper(context);
        int[] selectingPartIds = new int[] { R.id.headSelect, R.id.bodySelect, R.id.footSelect, R.id.wingSelect };
        int[] imgV_PetPartIds = new int[] { R.id.head_pet, R.id.body_pet, R.id.foot_pet, R.id.wing_pet };
        int[] petPartIds = this.getPetPartIds();
        Equipment[] petPartEqms = new Equipment[] { dbHelper.getCurrentHead(), dbHelper.getCurrentBody(), dbHelper.getCurrentFoot(), dbHelper.getCurrentWing() };

        this.imgB_selectingParts = new HashMap<>();
        this.imgV_PetParts = new HashMap<>();
        this.petParts = new HashMap<>();
        for (int i = 0, len = selectingPartIds.length; i < len; i++) {
            ImageButton tempB = (ImageButton)this.findViewById(selectingPartIds[i]);
            ImageView tempV = (ImageView)this.findViewById(imgV_PetPartIds[i]);
            this.imgB_selectingParts.put(selectingPartIds[i], tempB);
            this.imgV_PetParts.put(imgV_PetPartIds[i], tempV);
            this.petParts.put(petPartIds[i], petPartEqms[i]);
            petPartEqms[i].addImageToImageView(tempB, context);
            tempB.setOnClickListener(this);
            petPartEqms[i].addLargeImageToImageView(tempV, context);
        }

        setPetInfo();
    }

    private int[] getPetPartIds() {
        return new int[] { PetActivity.HEAD_EQM, PetActivity.BODY_EQM, PetActivity.FOOT_EQM, PetActivity.WING_EQM };
    }

    private void setPetInfo() {
        int[] petPartIds = getPetPartIds();
        int atk = 0;
        int def = 0;
        for (int i = 0, len = petPartIds.length; i < len; i++) {
            Equipment temp = petParts.get(petPartIds[i]);
            atk += temp.getAtk();
            def += temp.getDef();
        }

        petInfo.setText("Sức tấn công: " + atk + "\r\nSức phòng thủ: " + def);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.headSelect:
                openSelectEqmDialog(PetActivity.HEAD_EQM);
                break;
            case R.id.wingSelect:
                openSelectEqmDialog(PetActivity.WING_EQM);
                break;
            case R.id.bodySelect:
                openSelectEqmDialog(PetActivity.BODY_EQM);
                break;
            case R.id.footSelect:
                openSelectEqmDialog(PetActivity.FOOT_EQM);
                break;
        }
    }

    private void openSelectEqmDialog(int selectedPartId) {
        switch (selectedPartId) {
            case PetActivity.HEAD_EQM:
                selectingParts = new DBHelper(getApplicationContext()).getAllOwnedHead();
                break;
            case PetActivity.BODY_EQM:
                selectingParts = new DBHelper(getApplicationContext()).getAllOwnedBody();
                break;
            case PetActivity.FOOT_EQM:
                selectingParts = new DBHelper(getApplicationContext()).getAllOwnedFoot();
                break;
            case PetActivity.WING_EQM:
                selectingParts = new DBHelper(getApplicationContext()).getAllOwnedWing();
                break;
        }

        if (selectingParts.size() <= 0) {
            Toast.makeText(getApplicationContext(), "Bạn không có trang bị nào!", Toast.LENGTH_LONG).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.listview_holder, null);

            listV_selectingParts = (ListView)view.findViewById(R.id.listViewHolder);
            listV_selectingParts.setSelector(android.R.color.darker_gray);
            listV_selectingParts.setAdapter(new IconTextAdapter(getApplicationContext(), R.layout.icon_text_row, R.id.textViewRow, R.id.imageViewRow, selectingParts));
            listV_selectingParts.setOnItemClickListener(this);

            builder.setView(view)
                    .setTitle("Lựa chọn trang bị:")
                    .setPositiveButton("Chọn", this)
                    .setNegativeButton("Hủy", this)
                    .show();
            this.selectedPartId = selectedPartId;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE && selectedPart != null) {
            Context context = getApplicationContext();
            DBHelper dbHelper = new DBHelper(context);

            selectingParts.remove(selectedPart);
            dbHelper.deleteItem(selectedPart);

            if (selectedPartId != PetActivity.NO_EQM) {
                Equipment eqm = petParts.get(selectedPartId);
                selectingParts.add(eqm);
                dbHelper.saveItemToBag(eqm);
                petParts.put(selectedPartId, selectedPart);
            }

            switch (selectedPartId) {
                case PetActivity.HEAD_EQM:
                    selectedPart.addImageToImageView(imgB_selectingParts.get(R.id.headSelect), context);
                    selectedPart.addLargeImageToImageView(imgV_PetParts.get(R.id.head_pet), context);
                    dbHelper.saveCurrentHead(selectedPart);
                    break;
                case PetActivity.BODY_EQM:
                    selectedPart.addImageToImageView(imgB_selectingParts.get(R.id.bodySelect), context);
                    selectedPart.addLargeImageToImageView(imgV_PetParts.get(R.id.body_pet), context);
                    dbHelper.saveCurrentBody(selectedPart);
                    break;
                case PetActivity.FOOT_EQM:
                    selectedPart.addImageToImageView(imgB_selectingParts.get(R.id.footSelect), context);
                    selectedPart.addLargeImageToImageView(imgV_PetParts.get(R.id.foot_pet), context);
                    dbHelper.saveCurrentFoot(selectedPart);
                    break;
                case PetActivity.WING_EQM:
                    selectedPart.addImageToImageView(imgB_selectingParts.get(R.id.wingSelect), context);
                    selectedPart.addLargeImageToImageView(imgV_PetParts.get(R.id.wing_pet), context);
                    dbHelper.saveCurrentWing(selectedPart);
                    break;
            }

            setPetInfo();
        }

        listV_selectingParts = null;
        selectedPartId = PetActivity.NO_EQM;
        selectedPart = null;
        selectingParts = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedPart = (Equipment)listV_selectingParts.getItemAtPosition(position);
    }
}
