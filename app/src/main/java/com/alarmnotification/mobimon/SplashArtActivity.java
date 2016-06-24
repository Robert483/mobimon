package com.alarmnotification.mobimon;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import Object.GlobalContants;
import Object.DBHelper;


public class SplashArtActivity extends AppCompatActivity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_art);
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences data = getSharedPreferences(GlobalContants.USER_PREF, MODE_PRIVATE);
                if (!data.contains(GlobalContants.START_TIME)) {
                    long cur = System.currentTimeMillis();
                    data.edit()
                            .putLong(GlobalContants.LAST_ACTIVE, cur)
                            .putLong(GlobalContants.START_TIME, cur)
                            .putInt(GlobalContants.CUR_HP, 100)
                            .putInt(GlobalContants.MONEY, 200)
                            .commit();

                    try {
                        new DBHelper(getApplicationContext()).createDataBase();
                    } catch (Exception ex) {
                        throw new RuntimeException();
                    }
                }

                Intent mainIntent = new Intent(SplashArtActivity.this.getApplicationContext(), HomeActivity.class);
                SplashArtActivity.this.startActivity(mainIntent);
                SplashArtActivity.this.finish();
            }
        }, 3500);
    }
}
