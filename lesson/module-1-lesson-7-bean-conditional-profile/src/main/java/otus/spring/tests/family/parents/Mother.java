package otus.spring.tests.family.parents;

import org.springframework.stereotype.Component;
import otus.spring.tests.family.FamilyMember;

@Component
public class Mother extends FamilyMember {
    @Override
    public String getName() {
        return "Мама";
    }
}
