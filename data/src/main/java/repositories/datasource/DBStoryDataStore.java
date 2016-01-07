package repositories.datasource;

import com.activeandroid.query.Select;

import java.util.List;

import entities.StoryEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class DBStoryDataStore implements StoryDataStore {
    @Override
    public Observable<List<StoryEntity>> stories(final String registrationId) {

        return Observable.create(new Observable.OnSubscribe<List<StoryEntity>>() {
            @Override
            public void call(Subscriber<? super List<StoryEntity>> subscriber) {
                List<StoryEntity> storyEntities = new Select()
                        .from(StoryEntity.class)
                        .where("registration_user = ?" + registrationId)
                        .execute();
            }

        });
    }
}
