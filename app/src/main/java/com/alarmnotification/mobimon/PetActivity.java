package com.alarmnotification.mobimon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PetActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[] parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        int[] partIds = new int[] { R.id.headSelect, R.id.wingSelect, R.id.bodySelect, R.id.footSelect };

        parts = new ImageButton[partIds.length];
        for (int i = 0, len = partIds.length; i < len; ++i) {
            this.parts[i] = (ImageButton)this.findViewById(partIds[i]);
            this.parts[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.headSelect:
            case R.id.wingSelect:
            case R.id.bodySelect:
            case R.id.footSelect:
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
