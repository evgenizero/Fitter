package repositories.datasource.story;

import repositories.datasource.story.DBStoryDataStore;
import repositories.datasource.story.StoryDataStore;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class StoryDataStoreFactory {
    public StoryDataStore createDBDataStore() {
        return new DBStoryDataStore();
    }
}
