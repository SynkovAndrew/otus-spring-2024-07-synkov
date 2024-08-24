package otus.spring.conditionalsProfileExercise.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import otus.spring.conditionalsProfileExercise.model.base.Friend;

@Profile("Oleg")
@Component
public class Oleg extends Friend {
    @Override
    public String getName() {
        return "Олег";
    }
}
