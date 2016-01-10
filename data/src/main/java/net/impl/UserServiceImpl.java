package net.impl;

import net.services.UserService;

import entities.User;
import entities.UserEntity;
import mapper.UserEntitiyDataMapper;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by evgeniy on 1/9/16.
 */
public class UserServiceImpl extends DefaultImpl{

    private UserEntitiyDataMapper mapper = new UserEntitiyDataMapper();
    private UserService userService;

    public UserServiceImpl() {
        userService = retrofit.create(UserService.class);
    }

    @Override
    public Observable<User> registerUser(User user) {
        return userService.registerUser()
                .map(new Func1<UserEntity, User>() {
                    @Override
                    public User call(UserEntity userEntity) {
                        return mapper.transform(userEntity);
                    }
                });
    }

    @Override
    public Observable<User> user(String email) {
        return null;
    }

    @Override
    public Observable<User> createUser(User user) {
        return null;
    }
}
