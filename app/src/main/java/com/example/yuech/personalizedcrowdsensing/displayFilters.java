package com.example.yuech.personalizedcrowdsensing;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.tag;

public class displayFilters extends AppCompatActivity {

    int buttonId;

    int[] imgs = {R.drawable.price, R.drawable.category, R.drawable.parking, R.drawable.rating};
    int filtersNum;
    ArrayList<String> filters;
    ArrayAdapter<String> filtersAdapter;
    ListView showInput;

    // convert dp to pixels
    public int getPx(float dimensionDp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_filters);

        Intent intent = getIntent();
        buttonId = intent.getIntExtra(MainActivity.BUTTONID, 0);

        filtersNum = 0;
        showInput = (ListView)findViewById(R.id.showInput);
        filters =  new ArrayList<String>();
        filtersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filters);
        showInput.setAdapter(filtersAdapter);
    }

    public void addFilter(View view){

    }

    public void getFilter(View view){

        // get price range
        String minPrice = ((EditText)findViewById(R.id.minPrice)).getText().toString();
        String maxPrice = ((EditText)findViewById(R.id.maxPrice)).getText().toString();
        String[] priceRange = {minPrice, maxPrice};

        // get categories
        ArrayList<String> categories = new ArrayList<String>();
        GridLayout g = (GridLayout)findViewById(R.id.categoryFilters);
        int childCount = g.getChildCount();
        for(int i=0;i<childCount;i++){
            CheckBox c = (CheckBox)g.getChildAt(i);
            if(c.isChecked()) {
                categories.add(c.getText().toString());
                c.setTag("filter" + filtersNum);
            }
        }

        // get rating range
        ArrayList<String> ratings = new ArrayList<String>();
        g = (GridLayout)findViewById(R.id.ratingFilters);
        childCount = g.getChildCount();
        for(int i=0;i<childCount;i++){
            CheckBox c = (CheckBox)g.getChildAt(i);
            if(c.isChecked()) {
                ratings.add(c.getText().toString());
                c.setTag("filter" + filtersNum);
            }
        }

        // get parking price range
        ArrayList<String> parkingPrices = new ArrayList<String>();
        g = (GridLayout)findViewById(R.id.parkingFilters);
        childCount = g.getChildCount();
        for(int i=0;i<childCount;i++){
            CheckBox c = (CheckBox)g.getChildAt(i);
            if(c.isChecked()) {
                parkingPrices.add(c.getText().toString());
                c.setTag("filter" + filtersNum);
            }
        }

    }
}