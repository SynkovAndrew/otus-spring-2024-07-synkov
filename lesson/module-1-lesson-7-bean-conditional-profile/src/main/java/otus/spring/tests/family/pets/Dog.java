package otus.spring.tests.family.pets;

import org.springframework.stereotype.Component;
import otus.spring.tests.family.FamilyMember;

@Component
public class Dog extends FamilyMember {

    @Override
    public String getName() {
        return "Собака";
    }
}
