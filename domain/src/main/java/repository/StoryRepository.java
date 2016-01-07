package repository;

import java.util.List;

import entities.Story;
import rx.Observable;

/**
 * Created by evgeniy.yanev on 1/7/16.
 */
public interface StoryRepository {
    Observable<List<Story>> stories(String registrationId);
    Observable<Story> story(long storyId);
}
