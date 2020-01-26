package com.chromicle.collapp.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chromicle.collapp.R;
import com.chromicle.collapp.adapter.UserUploadedAdapter;
import com.chromicle.collapp.utils.UserUploaded;
import com.chromicle.collapp.utils.UserUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class SearchView extends AppCompatActivity {

    List<UserUploaded> booksArrayList;
    UserUtils user;
    UserUploadedAdapter userUploadedAdapter;

    DatabaseReference bookDb;

    @BindView(R.id.search)
    EditText searchView;

    @BindView(R.id.booksUploaded)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(SearchView.this));

        fetchBooks("fiction");
        fetchBooks("non-fiction");
        fetchBooks("bio");

        searchAdapter();
    }

    void filter(String text) {
        try {
            List<UserUploaded> temp = new ArrayList();
            for (UserUploaded d : booksArrayList) {
                // or use .equal(text) with you want equal match
                // use .toLowerCase() for better matches
                if (d.getBookName().contains(text)) {
                    temp.add(d);
                }
            }

            userUploadedAdapter.updateList(temp);

        } catch (Exception e) {
            Log.e("Search", e.getMessage().toString());
        }
    }

    private void searchAdapter() {
        searchView.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        // filter your list from your input
                        filter(s.toString());
                        // you can use runnable postDelayed like 500 ms to delay search text
                    }
                });
    }

    private void fetchBooks(String key) {
        user = new UserUtils();
        bookDb = FirebaseDatabase.getInstance().getReference(key);
        booksArrayList = new ArrayList<>();

        bookDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            UserUploaded bookValue = postSnapshot.getValue(UserUploaded.class);
                            booksArrayList.add(bookValue);
                        }

                        userUploadedAdapter = new UserUploadedAdapter(getApplicationContext(), booksArrayList);
                        recyclerView.setAdapter(userUploadedAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(SearchView.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
