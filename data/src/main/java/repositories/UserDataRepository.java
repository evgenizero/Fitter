package repositories;

import com.activeandroid.query.Select;

import entities.User;
import entities.UserEntity;
import mapper.UserEntitiyDataMapper;
import repositories.datasource.UserDataStoreFactory;
import repository.UserRepository;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class UserDataRepository implements UserRepository {

    private UserDataStoreFactory userDataStoreFactory;
    private UserEntitiyDataMapper userEntityDataMapper;

    public UserDataRepository() {
        userDataStoreFactory = new UserDataStoreFactory();
        userEntityDataMapper = new UserEntitiyDataMapper();
    }

    @Override
    public Observable<User> user(final String email) {
        return Observable.create(new Observable.OnSubscribe<UserEntity>() {
            @Override public void call(Subscriber<? super UserEntity> subscriber) {
                UserEntity userEntity = new Select()
                        .from(UserEntity.class)
                        .where("email = ?", email)
                        .executeSingle();

                if (userEntity != null) {
                    subscriber.onNext(userEntity);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Exception("User not found"));
                }
            }
        }).map(new Func1<UserEntity, User>() {
            @Override
            public User call(UserEntity userEntity) {
                return userEntityDataMapper.transform(userEntity);
            }
        });
    }

    @Override
    public Observable<Void> createUser(User user) {
        UserEntity userEntity = userEntityDataMapper.transform(user);
        userEntity.save();
        return null;
    }
}
