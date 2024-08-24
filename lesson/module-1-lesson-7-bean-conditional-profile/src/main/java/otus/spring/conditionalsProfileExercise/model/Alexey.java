package otus.spring.conditionalsProfileExercise.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import otus.spring.conditionalsProfileExercise.model.base.Friend;

@ConditionalOnProperty(name = "condition.alexey-exists", havingValue = "true")
@Component
public class Alexey extends Friend {
    @Override
    public String getName() {
        return "Алексей";
    }
}
