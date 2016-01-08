package interactors;

import entities.Story;
import repository.StoryRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by evgeniy.yanev on 1/8/16.
 */
public class CreateSingleStory extends UseCase {

    private final Story story;
    private final StoryRepository storyRepository;

    public CreateSingleStory(Story story, StoryRepository storyRepository, Scheduler threadExecutor, Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.story = story;
        this.storyRepository = storyRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return storyRepository.createStory(story);
    }
}
