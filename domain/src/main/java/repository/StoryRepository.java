package repository;

import java.util.List;

import entities.Story;
import rx.Observable;

/**
 * Created by evgeniy.yanev on 1/7/16.
 */
public interface StoryRepository {
    Observable<List<Story>> stories(long userId);
    Observable<Story> story(long storyId);
    Observable<List<Story>> createStories(List<Story> stories);
    Observable<Story> createStory(Story story);
}
