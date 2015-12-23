package com.fitter.views.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.fitter.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by evgeniy.yanev on 11/21/15.
 */
public class SignUpActivity extends Activity {
    @Bind(R.id.app_title)
    TextView mAppTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        mAppTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/grand_hotel.ttf"));

    }

}
