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
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.fitter.FitterApplication;
import com.fitter.R;
import com.fitter.mapper.UserModelToUser;
import com.fitter.model.UserModel;

import org.json.JSONException;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entities.User;
import interactors.CreateUser;
import interactors.DefaultSubscriber;
import interactors.UseCase;
import repositories.UserDataRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.app_title)
    View mAppTitle;

    CallbackManager mCallbackManager;
    FacebookCallback<LoginResult> mFacebookCallback;
    LoginManager mLoginManager;
    List<String> mPermissions = Arrays.asList("email", "user_friends", "public_profile", "user_birthday");

    private UseCase createUserUseCase;

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
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    // Application code
                    Log.v("LoginActivity", response.toString());
                    try {
                        String email = object.getString("email");
                        String name = object.getString("name");
                        String registrationId = object.getString("id");

                        UserModel userModel = new UserModel();
                        userModel.setFirstName(name);
                        userModel.setEmail(email);
                        userModel.setRegistrationId(registrationId);

                        createUserUseCase = new CreateUser(new UserDataRepository(), UserModelToUser.transform(userModel), Schedulers.newThread(), AndroidSchedulers.mainThread());
                        createUserUseCase.execute(new CreateUserSubscriber());

                    } catch (JSONException e) {
                        e.printStackTrace();
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_fb_btn)
    public void onLoginFbBtnClick() {
        mLoginManager.logInWithReadPermissions(this, mPermissions);
    }

    @RxLogSubscriber
    private final class CreateUserSubscriber extends DefaultSubscriber<User> {

        @Override public void onCompleted() {

        }

        @Override public void onError(Throwable e) {
        }

        @Override public void onNext(User user) {
            ((FitterApplication) getApplication()).setCurrentUser(user);

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (createUserUseCase != null) {
            createUserUseCase.unsubscribe();
        }
    }
}

