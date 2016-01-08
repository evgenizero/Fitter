package repositories.datasource;

import com.activeandroid.ActiveAndroid;
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
    public Observable<List<StoryEntity>> stories(final long userId) {

        return Observable.create(new Observable.OnSubscribe<List<StoryEntity>>() {
            @Override
            public void call(Subscriber<? super List<StoryEntity>> subscriber) {
                List<StoryEntity> storyEntities = new Select()
                        .from(StoryEntity.class)
                        .where("registration_user = " + userId)
                        .orderBy("createdDate DESC")
                        .execute();

                if (storyEntities != null && storyEntities.size() != 0) {
                    subscriber.onNext(storyEntities);
                } else {
                    subscriber.onError(new Exception("Error retrieving stories"));
                }

                subscriber.onCompleted();
            }

        });
    }

    @Override
    public Observable<List<StoryEntity>> createStories(final List<StoryEntity> stories) {
        return Observable.create(new Observable.OnSubscribe<List<StoryEntity>>() {
            @Override
            public void call(Subscriber<? super List<StoryEntity>> subscriber) {
                ActiveAndroid.beginTransaction();
                try {
                    for (StoryEntity storyEntity : stories) {
                        storyEntity.save();
                    }
                    ActiveAndroid.setTransactionSuccessful();
                    subscriber.onNext(stories);
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    ActiveAndroid.endTransaction();
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Override
    public Observable<StoryEntity> createStory(final StoryEntity storyEntity) {
        return Observable.create(new Observable.OnSubscribe<StoryEntity>() {
            @Override
            public void call(Subscriber<? super StoryEntity> subscriber) {
                Long id = storyEntity.save();
                if (id > 0) {
                    subscriber.onNext(storyEntity);
                } else {
                    subscriber.onError(new Exception("Could not create a new story"));
                }

                subscriber.onCompleted();
            }
        });
    }
}
