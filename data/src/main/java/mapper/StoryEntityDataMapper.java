package mapper;

import java.util.ArrayList;
import java.util.List;

import entities.Story;
import entities.StoryEntity;

/**
 * Created by evgeniy.yanev on 1/7/16.
 */
public class StoryEntityDataMapper {
    public Story transform(StoryEntity storyEntity) {
        Story story = null;
        if (storyEntity != null) {
            story = new Story();
            story.setText(storyEntity.text);
            story.setCreatedByUser(storyEntity.createdByUser);
            story.setCreatedDate(storyEntity.createdDate);
        }
        return story;
    }

    public StoryEntity transform(Story story) {
        StoryEntity storyEntity = null;
        if (story != null) {
            storyEntity = new StoryEntity();
            storyEntity.text = story.getText();
            storyEntity.createdByUser = story.getCreatedByUser();
            storyEntity.createdDate = story.getCreatedDate();
            storyEntity.registrationUser = story.getRegistrationUser();
        }

        return storyEntity;
    }

    public List<Story> transform(List<StoryEntity> storyEntities) {
        List<Story> stories = new ArrayList<>();
        Story story;
        for (StoryEntity entity : storyEntities) {
            story = transform(entity);
            if (story != null) {
                stories.add(story);
            }
        }

        return stories;
    }

    public List<StoryEntity> transformToEntity(List<Story> stories) {
        List<StoryEntity> storyEntities = new ArrayList<>();
        StoryEntity storyEntity;
        for (Story story : stories) {
            storyEntity = transform(story);
            if (storyEntity != null) {
                storyEntities.add(storyEntity);
            }
        }

        return storyEntities;
    }
}
