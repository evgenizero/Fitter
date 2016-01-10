package com.fitter.views.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.fitter.R;

import net.impl.UserServiceImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entities.User;
import interactors.DefaultSubscriber;
import interactors.RegisterUserUseCase;
import interactors.UseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by evgeniy.yanev on 11/21/15.
 */
public class SignUpActivity extends Activity {
    private UseCase mRegisterUserUseCase;

    @Bind(R.id.app_title)
    TextView mAppTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        mAppTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/grand_hotel.ttf"));

    }

    @OnClick(R.id.sign_up_btn)
    public void onSignUpBtnClick(View v) {
        mRegisterUserUseCase = new RegisterUserUseCase(null, new UserServiceImpl(), Schedulers.newThread(), AndroidSchedulers.mainThread());
        mRegisterUserUseCase.execute(new RegisterUserSubscriber());
    }

    @Override
    protected void onDestroy() {
        if (mRegisterUserUseCase != null) {
            mRegisterUserUseCase.unsubscribe();
        }
        super.onDestroy();
    }

    @RxLogSubscriber
    public class RegisterUserSubscriber extends DefaultSubscriber<User> {

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onNext(User user) {
            super.onNext(user);
        }
    }
}
