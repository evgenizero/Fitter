package interactors;

import entities.User;
import repository.UserRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by evgeniy.yanev on 1/6/16.
 */
public class CreateUser extends UseCase {
    private UserRepository userRepository;
    private User user;

    public CreateUser(UserRepository userRepository, User user, Scheduler subscribeOn, Scheduler observeOn) {
        super(subscribeOn, observeOn);
        this.userRepository = userRepository;
        this.user= user;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return userRepository.createUser(user);
    }
}
