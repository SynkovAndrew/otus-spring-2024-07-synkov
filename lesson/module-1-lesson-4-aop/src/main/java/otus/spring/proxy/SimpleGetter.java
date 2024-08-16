package otus.spring.proxy;

import java.util.Random;

public class SimpleGetter implements Getter {

    @Override
    public Integer getNumber() {
        return new Random().nextInt();
    }
}
