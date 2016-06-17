package com.alarmnotification.mobimon;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[] navs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int[] navIds = new int[] { R.id.petNav, R.id.bagNav, R.id.feedingNav, R.id.fightNav, R.id.facebookNav };
        this.navs = new ImageButton[navIds.length];
        for (int i = 0, len = navIds.length; i < len; i++) {
            this.navs[i] = (ImageButton)this.findViewById(navIds[i]);
            this.navs[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.petNav:
                this.goToActivity(PetActivity.class);
                break;
            case R.id.bagNav:
                goToActivity(BagActivity.class);
                break;
            case R.id.feedingNav:
                this.goToActivity(FeedingActivity.class);
                break;
            case R.id.fightNav:
                break;
            case R.id.facebookNav:
                break;
        }
    }

    private void goToActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this.getApplicationContext(), clazz);
        startActivity(intent);
    }
}
