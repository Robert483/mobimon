package com.alarmnotification.mobimon;

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

import Adapter.IconTextAdapter;
import Object.Equipment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        this.selectedPartId = PetActivity.NO_EQM;
        this.selectedPart = null;
        this.petInfo = (TextView)this.findViewById(R.id.petInfo);

        int[] selectingPartIds = new int[] { R.id.headSelect, R.id.bodySelect, R.id.footSelect, R.id.wingSelect };
        int[] imgV_PetPartIds = new int[] { R.id.head_pet, R.id.body_pet, R.id.foot_pet, R.id.wing_pet };
        int[] petPartIds = this.getPetPartIds();

        // ...
        Equipment[] petPartEqms = new Equipment[4];
        Bitmap temp = BitmapFactory.decodeResource(getResources(), R.drawable.head1);
        petPartEqms[0] = new Equipment();
        petPartEqms[0].setLargeImage(temp);
        petPartEqms[0].setImage(temp);
        petPartEqms[0].setAtk(1);
        petPartEqms[0].setDef(1);
        temp = BitmapFactory.decodeResource(getResources(), R.drawable.body1);
        petPartEqms[1] = new Equipment();
        petPartEqms[1].setLargeImage(temp);
        petPartEqms[1].setImage(temp);
        petPartEqms[1].setAtk(2);
        petPartEqms[1].setDef(2);
        temp = BitmapFactory.decodeResource(getResources(), R.drawable.foot1);
        petPartEqms[2] = new Equipment();
        petPartEqms[2].setLargeImage(temp);
        petPartEqms[2].setImage(temp);
        petPartEqms[2].setAtk(3);
        petPartEqms[2].setDef(3);
        temp = BitmapFactory.decodeResource(getResources(), R.drawable.wing1);
        petPartEqms[3] = new Equipment();
        petPartEqms[3].setLargeImage(temp);
        petPartEqms[3].setImage(temp);
        petPartEqms[3].setAtk(4);
        petPartEqms[3].setDef(4);
        // ...

        this.imgB_selectingParts = new HashMap<>();
        this.imgV_PetParts = new HashMap<>();
        this.petParts = new HashMap<>();
        for (int i = 0, len = selectingPartIds.length; i < len; i++) {
            ImageButton tempB = (ImageButton)this.findViewById(selectingPartIds[i]);
            ImageView tempV = (ImageView)this.findViewById(imgV_PetPartIds[i]);
            this.imgB_selectingParts.put(selectingPartIds[i], tempB);
            this.imgV_PetParts.put(imgV_PetPartIds[i], tempV);
            this.petParts.put(petPartIds[i], petPartEqms[i]);
            tempB.setImageBitmap(petPartEqms[i].getImage());
            tempB.setOnClickListener(this);
            tempV.setImageBitmap(petPartEqms[i].getLargeImage());
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

        petInfo.setText("Atk: " + atk + "\r\nDef: " + def);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.listview_holder, null);

        listV_selectingParts = (ListView)view.findViewById(R.id.listViewHolder);
        listV_selectingParts.setSelector(android.R.color.darker_gray);

        // ...
        Equipment[] petPartEqms = new Equipment[4];
        Bitmap temp = BitmapFactory.decodeResource(getResources(), R.drawable.head1);
        petPartEqms[0] = new Equipment();
        petPartEqms[0].setLargeImage(temp);
        petPartEqms[0].setImage(temp);
        petPartEqms[0].setAtk(1);
        petPartEqms[0].setDef(1);
        petPartEqms[0].setName("Head");
        temp = BitmapFactory.decodeResource(getResources(), R.drawable.body1);
        petPartEqms[1] = new Equipment();
        petPartEqms[1].setLargeImage(temp);
        petPartEqms[1].setImage(temp);
        petPartEqms[1].setAtk(2);
        petPartEqms[1].setDef(2);
        petPartEqms[1].setName("Body");
        temp = BitmapFactory.decodeResource(getResources(), R.drawable.foot1);
        petPartEqms[2] = new Equipment();
        petPartEqms[2].setLargeImage(temp);
        petPartEqms[2].setImage(temp);
        petPartEqms[2].setAtk(3);
        petPartEqms[2].setDef(3);
        petPartEqms[2].setName("Foot");
        temp = BitmapFactory.decodeResource(getResources(), R.drawable.wing1);
        petPartEqms[3] = new Equipment();
        petPartEqms[3].setLargeImage(temp);
        petPartEqms[3].setImage(temp);
        petPartEqms[3].setAtk(4);
        petPartEqms[3].setDef(4);
        petPartEqms[3].setName("Wing");
        // ...

        listV_selectingParts.setAdapter(new IconTextAdapter(getApplicationContext(), R.layout.icon_text_row, R.id.textViewRow, R.id.imageViewRow, petPartEqms));
        listV_selectingParts.setOnItemClickListener(this);

        builder.setView(view)
                .setTitle("Select equipment:")
                .setPositiveButton("Choose", this)
                .setNegativeButton("Cancel", this)
                .show();
        this.selectedPartId = selectedPartId;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            if (selectedPart != null) {
                switch (selectedPartId) {
                    case PetActivity.HEAD_EQM:
                        imgB_selectingParts.get(R.id.headSelect).setImageBitmap(selectedPart.getImage());
                        imgV_PetParts.get(R.id.head_pet).setImageBitmap(selectedPart.getLargeImage());
                        break;
                    case PetActivity.BODY_EQM:
                        imgB_selectingParts.get(R.id.bodySelect).setImageBitmap(selectedPart.getImage());
                        imgV_PetParts.get(R.id.body_pet).setImageBitmap(selectedPart.getLargeImage());
                        break;
                    case PetActivity.FOOT_EQM:
                        imgB_selectingParts.get(R.id.footSelect).setImageBitmap(selectedPart.getImage());
                        imgV_PetParts.get(R.id.foot_pet).setImageBitmap(selectedPart.getLargeImage());
                        break;
                    case PetActivity.WING_EQM:
                        imgB_selectingParts.get(R.id.wingSelect).setImageBitmap(selectedPart.getImage());
                        imgV_PetParts.get(R.id.wing_pet).setImageBitmap(selectedPart.getLargeImage());
                        break;
                }

                if (selectedPartId != PetActivity.NO_EQM) {
                    petParts.put(selectedPartId, selectedPart);
                }

                setPetInfo();
            }

            listV_selectingParts = null;
            selectedPartId = PetActivity.NO_EQM;
            selectedPart = null;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedPart = (Equipment)listV_selectingParts.getItemAtPosition(position);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // TODO: setCurretSet
    }
}
