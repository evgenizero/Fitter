package repositories.datasource.user;

import entities.UserEntity;
import rx.Observable;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class DBUserDataStore implements UserDataStore {
    @Override
    public Observable<UserEntity> user(String email) {
        return null;
    }
}
