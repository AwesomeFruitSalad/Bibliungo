package com.chromicle.collapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.chromicle.collapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class showBookActivity extends AppCompatActivity {


    @BindView(R.id.bookName)
    TextView bookName;


    @BindView(R.id.bookOwner)
    TextView bookOwner;

    @BindView(R.id.bookDes)
    TextView bookDes;

    @BindView(R.id.bookType)
    TextView bookType;

    @BindView(R.id.bookAmount)
    TextView bookAmount;

    @BindView(R.id.bookLocation)
    TextView bookLocation;

    @BindView(R.id.photo)
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);
        ButterKnife.bind(this);

        gettheValues();
    }

    private void gettheValues() {
        bookName.setText(getIntent().getStringExtra("BOOK_NAME"));
        bookOwner.setText(getIntent().getStringExtra("OWNER_NAME"));
        bookDes.setText(getIntent().getStringExtra("OWNER_DES"));
        bookType.setText(getIntent().getStringExtra("BOOK_TYPE"));
        bookAmount.setText(getIntent().getStringExtra("BOOK_COST"));
        bookLocation.setText(getIntent().getStringExtra("BOOK_LOCATION"));

        String book = getIntent().getStringExtra("BOOK_NAME");
        if (book.equals("Harry potter")){
            photo.setImageResource(R.drawable.harrypotter);
        }
        else if (book.equals("Chemistry TextBook")){
            photo.setImageResource(R.drawable.chem);
        }
        else if (book.equals("The Hobbit")){
            photo.setImageResource(R.drawable.hobbit);
        }
        else {
            photo.setImageResource(R.drawable.lordoflight);
        }
    }


}
