package otus.spring.lifecycles.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class Phone {
    private String greeting = "Погнали к родителям";

    private final PhoneNumber favoriteNumber;

    public void callFavoriteNumber() {
        System.out.println(favoriteNumber.getOwnerName() + " " + greeting);
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void customInitMethod() {
        System.out.println("Phone.customInitMethod");
    }


    public void customDestroyMethod() {
        System.out.println("Phone.customDestroyMethod");
    }

}
