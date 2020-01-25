package com.chromicle.collapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chromicle.collapp.R;
import com.chromicle.collapp.utils.BookUtils;
import com.chromicle.collapp.utils.UserUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBookActivity extends AppCompatActivity {


    @BindView(R.id.editTextName)
    EditText editTextBookName;

    @BindView(R.id.btn_location)
    Button btnLocation;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.editTextBookDes)
    EditText editTextBookDes;

    @BindView(R.id.editTextBookType)
    EditText editTextBookType;

    @BindView(R.id.editTextBookAmount)
    EditText editTextBookAmount;


    private UserUtils user;
    private String bookName, bookDes, bookType, bookAmount;
    DatabaseReference bookDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        ButterKnife.bind(this);
        user = new UserUtils();

    }

    @OnClick(R.id.btnSubmit)
    public void submitBook(View view) {

        getBookDetails();
        if (!checkErrors()) {
            return;
        }
        verifyDetails();
    }


    private void verifyDetails() {
        bookDb = FirebaseDatabase.getInstance().getReference(bookType);
        String id = bookDb.push().getKey();
        String bookLat = "13.007";
        String bookLong = "74.792";

        BookUtils bookDetails = new BookUtils(bookName, bookLat, bookLong, bookDes, bookAmount,user.getUserName());
        bookDb .child(id)
                .setValue(bookDetails)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddBookActivity.this, "Book Added", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddBookActivity.this, e.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
    }

    private boolean checkErrors() {
        EditText[] allFields = {
                editTextBookName, editTextBookDes, editTextBookType, editTextBookAmount
        };
        List<EditText> ErrorFields;
        ErrorFields = new ArrayList<EditText>();
        for (EditText edit : allFields) {
            if (TextUtils.isEmpty(edit.getText())) {
                ErrorFields.add(edit);
                for (int i = 0; i < ErrorFields.size(); i++) {
                    EditText currentField = ErrorFields.get(i);
                    currentField.setError("this field required");
                    currentField.requestFocus();
                    return false;
                }
            }
        }
        return true;
    }

    private void getBookDetails() {
        bookName = editTextBookName.getText().toString().trim();
        bookDes = editTextBookDes.getText().toString().trim();
        bookAmount = editTextBookAmount.getText().toString().trim();
        bookType = editTextBookType.getText().toString().trim();
    }
}
