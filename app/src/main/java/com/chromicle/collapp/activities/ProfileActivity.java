package com.chromicle.collapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.chromicle.collapp.R;
import com.chromicle.collapp.utils.UserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profileName)
    TextView profileName;

    @BindView(R.id.profileMail)
    TextView profileMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        UserUtils user = new UserUtils();

        profileName.setText(user.getDbUserName());
        profileMail.setText(user.getUserEmail());
    }
}
