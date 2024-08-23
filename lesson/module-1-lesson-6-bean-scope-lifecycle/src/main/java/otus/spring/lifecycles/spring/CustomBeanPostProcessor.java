package otus.spring.lifecycles.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import otus.spring.lifecycles.domain.Phone;

public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("phone")) {
            var phone = (Phone) bean;
            phone.setGreeting("нихуя себе!");

            System.out.println("postProcessBeforeInitialization");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("phone")) {
            System.out.println("postProcessAfterInitialization");
        }
        return bean;
    }
}
