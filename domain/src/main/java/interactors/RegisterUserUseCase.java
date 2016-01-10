package interactors;

import entities.User;
import repository.UserRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by evgeniy on 1/9/16.
 */
public class RegisterUserUseCase extends UseCase {

    private UserRepository repo;
    private User user;

    public RegisterUserUseCase(User user, UserRepository repo, Scheduler subscribeOn, Scheduler observeOn) {
        super(subscribeOn, observeOn);
        this.repo = repo;
        this.user = user;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repo.registerUser(user);
    }
}
