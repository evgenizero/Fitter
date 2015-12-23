package com.fitter.views.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fitter.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.app_title)
    View mAppTitle;

    CallbackManager mCallbackManager;
    FacebookCallback<LoginResult> mFacebookCallback;
    LoginManager mLoginManager;
    List<String> mPermissions = Arrays.asList("email", "user_friends", "public_profile", "user_birthday");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ((TextView) mAppTitle).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/grand_hotel.ttf"));

        mCallbackManager = CallbackManager.Factory.create();
        mFacebookCallback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        Log.v("LoginActivity", response.toString());
                        try {
                            String email = object.getString("email");
                            Log.v("LoginActivity", "obtained email: " + email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle params = new Bundle();
                params.putString("fields", "id, name, email, gender, birthday");
                request.setParameters(params);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };

        mLoginManager = LoginManager.getInstance();
        mLoginManager.registerCallback(mCallbackManager, mFacebookCallback);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.sign_up_btn)
    public void onSignUpBtnClick() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_btn)
    public void onLoginBtnClick() {
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);

        mLoginManager.logInWithReadPermissions(this, mPermissions);
    }

}

