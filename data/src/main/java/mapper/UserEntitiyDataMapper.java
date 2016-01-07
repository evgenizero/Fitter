package mapper;

import entities.User;
import entities.UserEntity;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class UserEntitiyDataMapper {
    public User transform(UserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            user = new User(userEntity.getId());
            user.setFirstName(userEntity.firstName);
            user.setLastName(userEntity.lastName);
            user.setEmail(userEntity.email);
        }

        return user;
    }

    public UserEntity transform(User user) {
        UserEntity userEntity = null;
        if (user != null) {
            userEntity = new UserEntity();
            userEntity.firstName = user.getFirstName();
            userEntity.lastName = user.getLastName();
            userEntity.email = user.getEmail();
        }

        return userEntity;
    }
}
