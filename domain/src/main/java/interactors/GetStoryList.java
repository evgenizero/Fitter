package interactors;

import entities.User;
import repository.StoryRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by evgeniy.yanev on 1/8/16.
 */
public class GetStoryList extends UseCase {

    private User user;
    private final StoryRepository storyRepository;

    public GetStoryList(User user, StoryRepository storyRepository, Scheduler threadExecutor, Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.user = user;
        this.storyRepository = storyRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return storyRepository.stories(user.getId());
    }
}
