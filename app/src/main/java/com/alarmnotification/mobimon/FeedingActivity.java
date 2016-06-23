package com.alarmnotification.mobimon;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import Object.Food;
import Adapter.ImageAdapter;

/**
 * Created by Thai Son on 04/06/2016.
 */
public class FeedingActivity extends AppCompatActivity implements OnItemClickListener, DialogInterface.OnClickListener {
    private GridView foodGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);

        // ...
        Food[] foods = new Food[4];
        foods[0] = new Food();
        foods[0].setName("Head");
        foods[0].setImage(BitmapFactory.decodeResource(getResources(), R.drawable.head1));
        foods[0].setHp(5);
        foods[0].setDescription("Head");
        foods[1] = new Food();
        foods[1].setName("Body");
        foods[1].setImage(BitmapFactory.decodeResource(getResources(), R.drawable.body1));
        foods[1].setHp(10);
        foods[1].setDescription("Body");
        foods[2] = new Food();
        foods[2].setName("Foot");
        foods[2].setImage(BitmapFactory.decodeResource(getResources(), R.drawable.foot1));
        foods[2].setHp(15);
        foods[2].setDescription("Foot");
        foods[3] = new Food();
        foods[3].setName("Wing");
        foods[3].setImage(BitmapFactory.decodeResource(getResources(), R.drawable.wing1));
        foods[3].setHp(20);
        foods[3].setDescription("Wing");
        // ...

        // Set custom adapter to gridview
        foodGrid = (GridView) findViewById(R.id.foodGrid);
        foodGrid.setAdapter(new ImageAdapter(this, foods));

        // Implement On Item click listener
        foodGrid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FeedingActivity.this);
        Food food = (Food)foodGrid.getItemAtPosition(position);
        builder.setTitle(food.getName())
                .setIcon(new BitmapDrawable(getResources(), food.getImage()))
                .setMessage("Item information")
                .setPositiveButton("Feed", this)
                .setNegativeButton("Cancel", this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {

        }
    }
}
