package otus.spring.conditionalsProfileExercise.model;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import otus.spring.conditionalsProfileExercise.model.base.Friend;
import otus.spring.conditionalsProfileExercise.model.conditions.YanaConditions;


@Conditional(YanaConditions.class)
@Component
public class Yana extends Friend {
    @Override
    public String getName() {
        return "Яна";
    }
}
