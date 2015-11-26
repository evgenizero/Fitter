package com.fitter.views.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fitter.R;
import com.kogitune.activity_transition.ActivityTransitionLauncher;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_bkg)
    SimpleDraweeView mLoginBg;

    @Bind(R.id.sign_up_btn)
    View mSignUpButton;

    @Bind(R.id.app_title)
    View mAppTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                ActivityTransitionLauncher.with(LoginActivity.this).from(v).launch(intent);
            }
        });

        ((TextView) mAppTitle).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/grand_hotel.ttf"));
    }

}

