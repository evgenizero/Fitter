package repositories.datasource;

import entities.UserEntity;
import rx.Observable;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public interface UserDataStore {
    Observable<UserEntity> user(String email);
}
