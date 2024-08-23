package otus.spring.lifecycles.spring;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.stereotype.Component;
import otus.spring.lifecycles.domain.Phone;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (beanFactory.containsBean("phone")) {
            System.out.println("postProcessBeanFactory");

            for (String beanName : beanFactory.getBeanDefinitionNames()) {
                if (beanName.equals("phone")) {
                    BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                    if (beanDefinition instanceof ScannedGenericBeanDefinition) {
                        ScannedGenericBeanDefinition scannedGenericBeanDefinition
                                = (ScannedGenericBeanDefinition) beanDefinition;
                        scannedGenericBeanDefinition.setBeanClass(Phone.class);
                        var classNameAttr = new BeanMetadataAttribute("className", Phone.class.getName());
                        scannedGenericBeanDefinition.addMetadataAttribute(classNameAttr);
                    }
                }
            }
        }
    }
}
