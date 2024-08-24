package otus.spring.tests.family.childrens;

import org.springframework.stereotype.Component;
import otus.spring.tests.family.FamilyMember;

@Component
public class Son extends FamilyMember {
    @Override
    public String getName() {
        return "Сын";
    }
}
