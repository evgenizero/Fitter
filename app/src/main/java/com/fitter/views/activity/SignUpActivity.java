package com.fitter.views.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fitter.R;
import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by evgeniy.yanev on 11/21/15.
 */
public class SignUpActivity extends Activity {
    @Bind(R.id.login_bg)
    SimpleDraweeView mLoginBg;

    @Bind(R.id.app_title)
    TextView mAppTitle;

    private ExitActivityTransition exitTransition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        exitTransition = ActivityTransition.with(getIntent()).to(mAppTitle).start(savedInstanceState);

        mAppTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/grand_hotel.ttf"));

    }

    @Override
    public void onBackPressed() {
        exitTransition.exit(this);
    }
}
