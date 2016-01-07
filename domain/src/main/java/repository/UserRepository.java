package repository;

import entities.User;
import rx.Observable;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public interface UserRepository {
    Observable<User> user(final String email);
    Observable<Void> createUser(User user);
}
