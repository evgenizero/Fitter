package repositories.datasource;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class StoryDataStoreFactory {
    public StoryDataStore createDBDataStore() {
        return new DBStoryDataStore();
    }
}
