package com.example.yuech.personalizedcrowdsensing;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String BUTTONID = "com.example.yuech.personalizedcrowdsensing.BUTTONID";

//    ArrayList<String> targets;
//    ArrayAdapter<String> targetsAdapter;
//    ListView showInput;
    LinearLayout showInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        showInput = (ListView)findViewById(R.id.showInput);
//        targets =  new ArrayList<String>();
//        targetsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, targets);
//        showInput.setAdapter(targetsAdapter);

        showInput = (LinearLayout)findViewById(R.id.showInput);
    }

//    protected void onAddTarget(View view){
//        EditText inputContainer = (EditText)findViewById(R.id.userInput);
//        String input = inputContainer.getText().toString();
//
//        if(!input.trim().isEmpty())
////            targetsAdapter.add(input);
//
//        inputContainer.setText("");
//    }

    protected void onChooseTarget(View view){
//        Intent intent = new Intent(this, displayFilters.class);
//        int buttonId = view.getId();
//        intent.putExtra(BUTTONID, buttonId);
//        startActivity(intent);
        GridLayout grid = (GridLayout)findViewById(R.id.grid);
        int rowNum = grid.getChildCount();
//        targetsAdapter.add(" "+rowNum);
        String target = "";

        for(int i=0;i<rowNum;i++){
            LinearLayout row = (LinearLayout)grid.getChildAt(i);
            int colNum = row.getChildCount();

            int j=0;
            for(j=0;j<colNum;j++) {
                RadioButton rbtn = (RadioButton) row.getChildAt(j);
                if (rbtn.isChecked()) {
                    target += rbtn.getText().toString() + ": ";
                    rbtn.setChecked(false);
                    break;
                }
            }
            if(j<colNum) break;
        }

        EditText minDist = (EditText)findViewById(R.id.minDist);
        EditText maxDist = (EditText)findViewById(R.id.maxDist);

        String min = minDist.getText().toString();
        String max = maxDist.getText().toString();
        minDist.setText("");
        maxDist.setText("");
        target += min+" - "+max+" km";

//        targetsAdapter.add(target);

        LinearLayout rowOuter = new LinearLayout(this);
        rowOuter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        RelativeLayout rowInner = new RelativeLayout(this);
        rowInner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView t = new TextView(this);
        int size = (int)getResources().getDimension(R.dimen.smBtn);
        t.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, size));
        t.setGravity(Gravity.START);
        t.setText(target);
        t.setTextAppearance(this, android.R.style.TextAppearance_Large);

        Button b = new Button(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(size, size);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        b.setLayoutParams(lp);
        b.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.delete, null));

        rowInner.addView(t);
        rowInner.addView(b);
        rowOuter.addView(rowInner);
        showInput.addView(rowOuter);

        Toast toast = Toast.makeText(this, "Found a police in 3 km", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

}
