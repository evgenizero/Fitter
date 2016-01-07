package repositories;

import java.util.List;

import entities.Story;
import entities.StoryEntity;
import mapper.StoryEntityDataMapper;
import repositories.datasource.StoryDataStore;
import repositories.datasource.StoryDataStoreFactory;
import repository.StoryRepository;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by evgeniy.yanev on 1/7/16.
 */
public class StoryDataRepository implements StoryRepository {
    private StoryDataStoreFactory storyDataStoreFactory;
    private StoryEntityDataMapper storyEntityDataMapper;

    public StoryDataRepository() {
        storyDataStoreFactory = new StoryDataStoreFactory();
        storyEntityDataMapper = new StoryEntityDataMapper();
    }

    @Override
    public Observable<List<Story>> stories(String registrationId) {
        StoryDataStore dbDataStore = storyDataStoreFactory.createDBDataStore();
        return dbDataStore.stories(registrationId)
                .map(new Func1<List<StoryEntity>, List<Story>>() {
                    @Override
                    public List<Story> call(List<StoryEntity> storyEntities) {
                        return storyEntityDataMapper.transform(storyEntities);
                    }
                });
    }

    @Override
    public Observable<Story> story(long storyId) {
        return null;
    }
}
