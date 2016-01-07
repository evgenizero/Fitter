package mapper;

import java.util.ArrayList;
import java.util.List;

import entities.Story;
import entities.StoryEntity;

/**
 * Created by evgeniy.yanev on 1/7/16.
 */
public class StoryEntityDataMapper {
    public static Story transform(StoryEntity storyEntity) {
        Story story = null;
        if (storyEntity != null) {
            story = new Story();
            story.setText(storyEntity.text);
            story.setAttachmentUrls(storyEntity.attachmentUrls);
            story.setCreatedByUser(storyEntity.createdByUser);
            story.setCreatedDate(storyEntity.createdDate);
        }
        return story;
    }

    public static List<Story> transform(List<StoryEntity> storyEntities) {
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
}
