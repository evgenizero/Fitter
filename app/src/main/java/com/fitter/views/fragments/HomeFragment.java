package com.fitter.views.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.fitter.FitterApplication;
import com.fitter.R;
import com.fitter.mapper.StoryModelMapper;
import com.fitter.views.adapter.StoriesAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entities.Story;
import interactors.CreateStories;
import interactors.DefaultSubscriber;
import interactors.GetStoryList;
import repositories.StoryDataRepository;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by evgeniy.yanev on 11/28/15.
 */
public class HomeFragment extends Fragment {

    @Bind(R.id.newsfeed_recyclerView)
    RecyclerView mNewsFeedRecyclerView;

    private GetStoryList getStoryList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getStoryList = new GetStoryList(((FitterApplication) getActivity().getApplication()).getCurrentUser(), new StoryDataRepository(), Schedulers.newThread(), AndroidSchedulers.mainThread());
        getStoryList.execute(new StorySubscriber());
    }

    private void loadList() {
        getStoryList = new GetStoryList(((FitterApplication) getActivity().getApplication()).getCurrentUser(), new StoryDataRepository(), Schedulers.newThread(), AndroidSchedulers.mainThread());
        getStoryList.execute(new StorySubscriber());
    }

    @RxLogSubscriber
    public class StorySubscriber extends DefaultSubscriber<List<Story>> {

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.d("HomeFragment", "no stories found, first create some");
            List<Story> stories = new ArrayList<>();
            Story story;
            for (int i = 0; i < 100; i++) {
                story = new Story();
                story.setText("Story text " + i);
                story.setRegistrationUser(((FitterApplication)getActivity().getApplication()).getCurrentUser().getId());
                story.setCreatedDate(new Date());
                story.setCreatedByUser(story.getRegistrationUser());
                story.setAttachmentUrls(new ArrayList<>());
                stories.add(story);
            }

            CreateStories createStories = new CreateStories(stories, new StoryDataRepository(), Schedulers.newThread(), AndroidSchedulers.mainThread());
            createStories.execute(new CreateStorySubscriber());

        }

        @Override
        public void onNext(List<Story> stories) {
            super.onNext(stories);
            Log.d("HomeFragment", "render to view");
            StoriesAdapter adapter = new StoriesAdapter(new StoryModelMapper().transform(stories));
            mNewsFeedRecyclerView.setAdapter(adapter);
        }
    }

    @RxLogSubscriber
    public class CreateStorySubscriber extends DefaultSubscriber<List<Story>> {
        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onNext(List<Story> stories) {
            super.onNext(stories);

            StoriesAdapter adapter = new StoriesAdapter(new StoryModelMapper().transform(stories));
            mNewsFeedRecyclerView.setAdapter(adapter);

            Log.d("HomeFragment", "render to view");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        // Inflate the layout for this fragment

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNewsFeedRecyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        if (getStoryList != null) {
            getStoryList.unsubscribe();
        }
        super.onDestroyView();
    }

    @OnClick(R.id.fab)
    public void onClickCreateStoryButton(View v) {
        Fragment fragment = new CreateStoryFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
