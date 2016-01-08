package com.fitter.mapper;

import com.fitter.model.StoryModel;

import java.util.ArrayList;
import java.util.List;

import entities.Story;

/**
 * Created by evgeniy.yanev on 1/8/16.
 */
public class StoryModelMapper {
    public List<StoryModel> transform(List<Story> stories) {
        List<StoryModel> storyModels = new ArrayList<>();
        StoryModel storyModel;
        for (Story story : stories) {
            storyModel = transform(story);
            storyModels.add(storyModel);
        }

        return storyModels;
    }

    private StoryModel transform(Story story) {
        StoryModel storyModel = null;
        if (story != null) {
            storyModel = new StoryModel();
            storyModel.setText(story.getText());
            storyModel.setCreatedDate(story.getCreatedDate());
        }
        return storyModel;
    }
}
