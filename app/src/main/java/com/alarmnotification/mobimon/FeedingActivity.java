package com.alarmnotification.mobimon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;

import java.util.ArrayList;

import Adapter.IconTextAdapter;
import Object.GlobalContants;
import Object.Food;
import Object.DBHelper;

/**
 * Created by Thai Son on 04/06/2016.
 */
public class FeedingActivity extends AppCompatActivity implements OnItemClickListener, DialogInterface.OnClickListener {
    private static final int RESET_DIALOG = 1;
    private static final int FEEDING_DIALOG = 0;
    private static final int NO_DIALOG = -1;

    private ArrayList<Food> foods;
    private GridView foodGrid;
    private ProgressBar hpBar;
    private int curHp;
    private Food selectedFood;
    private Handler hpTick;
    private Runnable hpDrop;
    private PendingIntent alarmIntent;
    private int selectedDialog;
    private boolean alarmState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);

        alarmState = GlobalContants.SET_ALARM_ENABLED;
        selectedDialog = FeedingActivity.NO_DIALOG;
        selectedFood = null;
        foods = new DBHelper(getApplicationContext()).getAllOwnedFood();

        foodGrid = (GridView)findViewById(R.id.foodGrid);
        foodGrid.setAdapter(new IconTextAdapter(getApplicationContext(), R.layout.gridview_row, R.id.textView_TS, R.id.imageView_TS, foods));
        foodGrid.setOnItemClickListener(this);

        hpBar = (ProgressBar)findViewById(R.id.hpBar_feeding);

        hpTick = new Handler();
        hpDrop = new Runnable() {
            @Override
            public void run() {
                if (--curHp <= 0) {
                    resetGame();
                } else {
                    hpTick.postDelayed(this, GlobalContants.HP_DROP_INTERVAL);
                    hpBar.setProgress(curHp);
                }
            }
        };

        Context context = getApplicationContext();
        Intent intent = new Intent(context, HungerReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, GlobalContants.HUNGER_ALARM, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void resetGame() {
        selectedDialog = FeedingActivity.RESET_DIALOG;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xin lỗi!")
                .setMessage("Thú cưng của bạn đã chết đói. Bạn sẽ quay trở về khởi điểm, tất cả vật phẩm đã mất. Bạn sẽ nuôi một thú cưng mới, đừng để thú của bạn chết đói lần nữa!")
                .setPositiveButton("Tôi đã hiểu", this)
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void calculateFromLastTime() {
        SharedPreferences data = getSharedPreferences(GlobalContants.USER_PREF, MODE_PRIVATE);
        long cur = System.currentTimeMillis();
        long startTime = data.getLong(GlobalContants.START_TIME, 0);
        curHp = data.getInt(GlobalContants.CUR_HP, 100);
        curHp -= (cur - startTime) / GlobalContants.HP_DROP_INTERVAL - (data.getLong(GlobalContants.LAST_ACTIVE, 0) - startTime) / GlobalContants.HP_DROP_INTERVAL;

        if (curHp <= 0) {
            resetGame();
        } else {
            long timeLeft = GlobalContants.HP_DROP_INTERVAL - (cur - startTime) % GlobalContants.HP_DROP_INTERVAL;
            hpBar.setProgress(curHp);
            hpTick.postDelayed(hpDrop, timeLeft);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedDialog = FeedingActivity.FEEDING_DIALOG;

        AlertDialog.Builder builder = new AlertDialog.Builder(FeedingActivity.this);
        selectedFood = (Food)foodGrid.getItemAtPosition(position);
        builder.setTitle(selectedFood.getName())
                .setIcon(new BitmapDrawable(getResources(), selectedFood.getImage()))
                .setMessage(selectedFood.getDescription())
                .setPositiveButton("Cho ăn", this)
                .setNegativeButton("Hủy", this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (selectedDialog) {
            case FeedingActivity.FEEDING_DIALOG:
                if (which == DialogInterface.BUTTON_POSITIVE && selectedFood != null) {
                    curHp += selectedFood.getHp();
                    if (curHp > 100) {
                        curHp = 100;
                    }
                    hpBar.setProgress(curHp);
                    foods.remove(selectedFood);
                    new DBHelper(getApplicationContext()).deleteItem(selectedFood);
                    foodGrid.invalidateViews();
                }

                selectedFood = null;
                break;
            case FeedingActivity.RESET_DIALOG:
                SharedPreferences data = getSharedPreferences(GlobalContants.USER_PREF, MODE_PRIVATE);
                data.edit()
                        .putLong(GlobalContants.START_TIME, System.currentTimeMillis())
                        .commit();

                try {
                    new DBHelper(getApplicationContext()).copyDataBase();
                } catch (Exception ex) {
                    throw new RuntimeException();
                }

                foods.clear();
                foodGrid.invalidateViews();
                curHp = 100;
                hpBar.setProgress(curHp);
                hpTick.postDelayed(hpDrop, GlobalContants.HP_DROP_INTERVAL);
                break;
        }

        selectedDialog = FeedingActivity.NO_DIALOG;
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculateFromLastTime();
        AlarmManager alarmMgr = (AlarmManager)getApplicationContext().getSystemService(ALARM_SERVICE);
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
            alarmState = GlobalContants.SET_ALARM_DISABLED;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        setAlarm();
    }

    private void setAlarm() {
        if (alarmState == GlobalContants.SET_ALARM_DISABLED) {
            hpTick.removeCallbacks(hpDrop);

            long cur = System.currentTimeMillis();
            int healthState = GlobalContants.FULL_HEALTH;
            SharedPreferences data = getSharedPreferences(GlobalContants.USER_PREF, MODE_PRIVATE);
            long timeLeft = GlobalContants.HP_DROP_INTERVAL - (cur - data.getLong(GlobalContants.START_TIME, 0)) % GlobalContants.HP_DROP_INTERVAL;

            if (curHp > 50) {
                timeLeft += (curHp - 51) * GlobalContants.HP_DROP_INTERVAL;
            } else if (curHp > 20){
                timeLeft += (curHp - 21) * GlobalContants.HP_DROP_INTERVAL;
                healthState = GlobalContants.HALF_HEALTH;
            } else {
                timeLeft += (curHp - 1) * GlobalContants.HP_DROP_INTERVAL;
                healthState = GlobalContants.NO_HEALTH;
            }

            data.edit()
                    .putLong(GlobalContants.LAST_ACTIVE, cur)
                    .putInt(GlobalContants.CUR_HP, curHp)
                    .putInt(GlobalContants.HEALTH_STATE, healthState)
                    .commit();

            ((AlarmManager)getApplicationContext().getSystemService(ALARM_SERVICE)).set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + timeLeft, alarmIntent);
            alarmState = GlobalContants.SET_ALARM_ENABLED;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setAlarm();
    }
}
