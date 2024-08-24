package otus.spring.conditionalsProfileExercise.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import otus.spring.conditionalsProfileExercise.model.base.Friend;

@ConditionalOnProperty(name = "condition.yanis-exists", havingValue = "true")
@Component
public class Yanis extends Friend {
    @Override
    public String getName() {
        return "Янис";
    }
}
