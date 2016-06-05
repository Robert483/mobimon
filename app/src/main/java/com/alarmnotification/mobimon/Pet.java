package com.alarmnotification.mobimon;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Pet extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[] equipments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        int[] eqmsId = new int[] { R.id.equipment1, R.id.equipment2, R.id.equipment3, R.id.equipment4 };

        equipments = new ImageButton[eqmsId.length];
        for (int i = 0, len = eqmsId.length; i < len; ++i) {
            this.equipments[i] = (ImageButton)this.findViewById(eqmsId[i]);
            this.equipments[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.equipment1:
            case R.id.equipment2:
            case R.id.equipment3:
            case R.id.equipment4:
                openSelectEqmDialog();
                break;
        }
    }

    private void openSelectEqmDialog() {
        int[]    eqmIcons = this.getEqmIcons();
        String[] eqmNames = this.getEqmNames();

        ItemSelectDialog.newInstance(eqmNames, eqmIcons).show(this.getSupportFragmentManager(), "Select Equipment");
    }

    private String[] getEqmNames() {
        return new String[] { "india", "Brazil", "Canada", "China", "France", "Germany", "Iran", "Italy", "Japan", "Korea", "Mexico", "Netherlands", "Portugal", "Russia", "Saudi Arabia" };
    }

    private int[] getEqmIcons() {
        return new int[] { R.drawable.india, R.drawable.brazil, R.drawable.canada, R.drawable.china, R.drawable.france, R.drawable.germany, R.drawable.iran, R.drawable.italy, R.drawable.japan, R.drawable.korea, R.drawable.mexico, R.drawable.netherlands, R.drawable.portugal, R.drawable.russia, R.drawable.saudi_arabia };
    }
}
