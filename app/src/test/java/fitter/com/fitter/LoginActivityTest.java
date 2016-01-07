package fitter.com.fitter;

import com.fitter.BuildConfig;
import com.fitter.R;
import com.fitter.views.activity.LoginActivity;
import com.fitter.views.activity.SignUpActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

/**
 * Created by evgeniy.yanev on 12/23/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class LoginActivityTest {

    @Test
    public void clickingSignUp_shouldStartSignUpActivity() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        activity.findViewById(R.id.sign_up_btn).performClick();
        Assert.assertEquals(Shadows.shadowOf(activity).peekNextStartedActivity().getComponent().getClassName(), SignUpActivity.class.getName());
    }
}
