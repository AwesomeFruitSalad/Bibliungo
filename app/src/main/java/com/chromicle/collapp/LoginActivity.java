package com.chromicle.collapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Chromicle (ajayprabhakar369@gmail.com)
 * @since 25/01/2020
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.sign_in_button)
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        signInButton.setOnClickListener(new UserSignIn());
        isUserSignin();
    }


    private void isUserSignin() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            List<AuthUI.IdpConfig> signInIntentBuilders =
                    Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(signInIntentBuilders)
                            .setIsSmartLockEnabled(false)
                            .setLogo(R.mipmap.ic_launcher)
                            .build(),
                    200);
        } else if (FirebaseAuth.getInstance().getCurrentUser().getDisplayName() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    private class UserSignIn implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            isUserSignin();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }
    }
}