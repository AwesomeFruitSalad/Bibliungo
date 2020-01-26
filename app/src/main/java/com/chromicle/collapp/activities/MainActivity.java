package com.chromicle.collapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.chromicle.collapp.R;
import com.chromicle.collapp.utils.ActivityUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.Menu;
import android.view.MenuItem;
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

    private MenuItem profileMenu;

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


    @OnClick(R.id.btnBiographies)
    public void buttonBiographies(){
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("ID", "bio");
        startActivity(intent);
    }


    @OnClick(R.id.btnNonFiction)
    public void buttonNonFiction(){
        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("ID", "non-fiction");
        startActivity(intent);
    }

    @OnClick(R.id.bookHarry)
    public void buttonHarry(){
        Intent intent = new Intent(MainActivity.this, showBookActivity.class);
        intent.putExtra("OWNER_NAME", "vaishnavs4201");
        intent.putExtra("BOOK_NAME", "Harry potter");
        intent.putExtra("OWNER_DES", "This is great book written by JK rowling");
        intent.putExtra("BOOK_TYPE", "fiction");
        intent.putExtra("BOOK_COST", "RS: 800");
        intent.putExtra("BOOK_LOCATION", "13.007  74.792");
        startActivity(intent);
    }


    @OnClick(R.id.bookChem)
    public void buttonChem() {
        Intent intent = new Intent(MainActivity.this, showBookActivity.class);
        intent.putExtra("OWNER_NAME", "ajayprabhakar369");
        intent.putExtra("BOOK_NAME", "Chemistry TextBook");
        intent.putExtra("OWNER_DES", "This is usefull for +12");
        intent.putExtra("BOOK_TYPE", "textbook");
        intent.putExtra("BOOK_COST", "RS: 350");
        intent.putExtra("BOOK_LOCATION", "13.007  74.792");
        startActivity(intent);
    }


    @OnClick(R.id.bookHobbit)
    public void buttonHobbit(){
        Intent intent = new Intent(MainActivity.this, showBookActivity.class);
        intent.putExtra("OWNER_NAME", "vaishnavs4201");
        intent.putExtra("BOOK_NAME", "The Hobbit");
        intent.putExtra("OWNER_DES", "It is the prequel to Lord of Rings");
        intent.putExtra("BOOK_TYPE", "fiction");
        intent.putExtra("BOOK_COST", "RS: 966");
        intent.putExtra("BOOK_LOCATION", "13.007  74.792");
        startActivity(intent);
    }

    @OnClick(R.id.bookLord)
    public void buttonLord(){
        Intent intent = new Intent(MainActivity.this, showBookActivity.class);
        intent.putExtra("OWNER_NAME", "ajayprabhakar369");
        intent.putExtra("BOOK_NAME", "The Lord");
        intent.putExtra("OWNER_DES", "award winning science fiction book");
        intent.putExtra("BOOK_TYPE", "fiction");
        intent.putExtra("BOOK_COST", "RS: 777");
        intent.putExtra("BOOK_LOCATION", "13.007  74.792");
        startActivity(intent);
    }


    @OnClick(R.id.btnTextBooks)
    public void buttonTextbook(){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("ID", "textbook");
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        profileMenu = menu.findItem(R.id.profile);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final ProgressDialog progressDialog;

        //noinspection SimplifiableIfStatement
        if (id == R.id.profile) {
            ActivityUtils.launchActivity(MainActivity.this, ProfileActivity.class,false);
        }

        return true;
    }

}
