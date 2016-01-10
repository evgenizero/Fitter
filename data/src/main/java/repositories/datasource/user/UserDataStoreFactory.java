package repositories.datasource.user;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class UserDataStoreFactory {
    public UserDataStore createDBDataStore() {
        return new DBUserDataStore();
    }
}
