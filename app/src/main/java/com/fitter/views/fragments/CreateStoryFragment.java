package com.fitter.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.fitter.FitterApplication;
import com.fitter.R;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entities.Story;
import entities.User;
import interactors.CreateSingleStory;
import interactors.DefaultSubscriber;
import repositories.StoryDataRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by evgeniy.yanev on 1/8/16.
 */
public class CreateStoryFragment extends Fragment {
    private CreateSingleStory mCreateSingleStoryUseCase;

    @Bind(R.id.storyText)
    TextView mStoryText;

    @Bind(R.id.profile_image)
    SimpleDraweeView mProfileImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_story, container, false);
        ButterKnife.bind(this, rootView);

        String userID = ((FitterApplication)getActivity().getApplication()).getCurrentUser().getRegistrationId();
        Uri uri = Uri.parse("https://graph.facebook.com/" + userID + "/picture?type=large");
        mProfileImage.setImageURI(uri);

        return rootView;
    }

    @OnClick(R.id.fab)
    public void onCreateNewStoryClick(View view) {

        User currentUser = ((FitterApplication)getActivity().getApplication()).getCurrentUser();

        Story story = new Story();
        story.setText(mStoryText.getText().toString());
        story.setCreatedDate(new Date());
        story.setRegistrationUser(currentUser.getId());
        story.setCreatedByUser(currentUser.getId());

        mCreateSingleStoryUseCase = new CreateSingleStory(story, new StoryDataRepository(), Schedulers.newThread(), AndroidSchedulers.mainThread());
        mCreateSingleStoryUseCase.execute(new CreateStorySubscriber(getActivity()));
    }

    @Override
    public void onDestroyView() {
        if (mCreateSingleStoryUseCase != null) {
            mCreateSingleStoryUseCase.unsubscribe();
        }
        super.onDestroyView();
    }

    @RxLogSubscriber
    public class CreateStorySubscriber extends DefaultSubscriber<Story> {

        private Context context;

        public CreateStorySubscriber(Context context) {
            this.context = context;
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mStoryText.getWindowToken(), 0);
        }

        @Override
        public void onNext(Story story) {
            super.onNext(story);
            ((AppCompatActivity)context).getSupportFragmentManager().popBackStack();
            Toast.makeText(context, "Story created succesfully", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);

            Toast.makeText(context, "Could not create a new story", Toast.LENGTH_SHORT).show();
        }
    }
}
