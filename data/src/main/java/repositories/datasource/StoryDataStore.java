package repositories.datasource;

import java.util.List;

import entities.StoryEntity;
import rx.Observable;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public interface StoryDataStore {
    Observable<List<StoryEntity>> stories(String registrationId);
}
