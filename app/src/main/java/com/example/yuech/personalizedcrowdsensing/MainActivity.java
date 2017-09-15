package com.example.yuech.personalizedcrowdsensing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioButton rBtn;
    LinearLayout showInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showInput = (LinearLayout)findViewById(R.id.showInput);
        rBtn = null;
    }

    protected void onChooseTarget(View view){
        rBtn = (RadioButton)view;
    }

    protected void submitQuery(View view) {
        if(rBtn != null) {
            String target = "";
            target += rBtn.getTag().toString() + ": ";
            rBtn.setChecked(false);
            rBtn = null;

            EditText minDist = (EditText) findViewById(R.id.minDist), maxDist = (EditText) findViewById(R.id.maxDist);

            String min = minDist.getText().toString(), max = maxDist.getText().toString();
            minDist.setText("");
            maxDist.setText("");
            target += min + " - " + max + " km";

            LinearLayout rowOuter = new LinearLayout(this);
            rowOuter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            RelativeLayout rowInner = new RelativeLayout(this);
            rowInner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            TextView t = new TextView(this);
            int size = (int) getResources().getDimension(R.dimen.smBtn);
            t.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, size));
            t.setGravity(Gravity.START);
            t.setText(target);
            t.setTextAppearance(this, android.R.style.TextAppearance_Large);

            Button b = new Button(this);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(size, size);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            b.setLayoutParams(lp);
            b.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.delete, null));
            b.setTag(rowOuter);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInput.removeView((LinearLayout) ((Button) v).getTag());
                }
            });

            rowInner.addView(t);
            rowInner.addView(b);
            rowOuter.addView(rowInner);
            showInput.addView(rowOuter);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(getApplicationContext(), "Found a police in 3 km", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();
                        }
                    });
                }
            }).start();
        }
    }
}
