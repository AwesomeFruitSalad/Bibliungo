package com.chromicle.collapp.activities;

import android.os.Bundle;
import android.widget.TextView;
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

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profileName)
    TextView profileName;

    @BindView(R.id.profileMail)
    TextView profileMail;

    @BindView(R.id.booksUploaded)
    RecyclerView booksUploadedRecycleView;

    DatabaseReference bookDb;

    List<UserUploaded> booksArrayList;
    UserUtils user;
    UserUploadedAdapter userUploadedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        UserUtils user = new UserUtils();

        addFirebaseInstance();

        profileName.setText(user.getDbUserName());
        profileMail.setText(user.getUserEmail());

        fetchBooks();
    }

    private void addFirebaseInstance() {
        user = new UserUtils();
        bookDb = FirebaseDatabase.getInstance().getReference("fiction");
    }

    private void fetchBooks() {

        booksUploadedRecycleView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

        booksArrayList = new ArrayList<>();

        bookDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            UserUploaded bookValue = postSnapshot.getValue(UserUploaded.class);
                            Toast.makeText(ProfileActivity.this, "I am here", Toast.LENGTH_SHORT).show();
                            if (bookValue != null) {
                                if (bookValue.getUser().equals(user.getDbUserName())) {
                                    booksArrayList.add(bookValue);
                                }
                            }
                        }

                        userUploadedAdapter = new UserUploadedAdapter(getApplicationContext(), booksArrayList);
                        booksUploadedRecycleView.setAdapter(userUploadedAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ProfileActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}
