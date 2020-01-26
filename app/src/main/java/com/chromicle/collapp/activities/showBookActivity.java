package com.chromicle.collapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }


}
