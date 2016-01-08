package interactors;

import java.util.List;

import entities.Story;
import repository.StoryRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by evgeniy.yanev on 1/8/16.
 */
public class CreateStories extends UseCase {
    private final List<Story> stories;
    private StoryRepository storyRepository;

    public CreateStories(List<Story> stories, StoryRepository storyRepository, Scheduler threadExecutor, Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.stories = stories;
        this.storyRepository = storyRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return storyRepository.createStories(stories);
    }
}
