package com.chromicle.collapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.chromicle.collapp.R;
import com.chromicle.collapp.utils.ActivityUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.launchActivity(MainActivity.this, AddBookActivity.class,false);
            }
        });
    }


    @OnClick(R.id.btnFiction)
    public void buttonFiction(){
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("ID", "fiction");
        startActivity(intent);
    }



    @OnClick(R.id.btnTextBooks)
    public void buttonTextbook(){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("ID", "textbook");
        startActivity(intent);
    }

}
