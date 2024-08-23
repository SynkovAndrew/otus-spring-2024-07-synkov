package otus.spring.lifecycles.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import otus.spring.lifecycles.domain.Phone;

public class CustomBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(
            AnnotationMetadata importingClassMetadata,
            BeanDefinitionRegistry registry
    ) {
        System.out.println("ImportBeanDefinitionRegistrar.registerBeanDefinitions()");
        registerBeanFactoryPostProcessor(registry);
        registerBeanPostProcessor(registry);
        registerPhoneBean(registry);
    }

    private void registerPhoneBean(BeanDefinitionRegistry registry) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(Phone.class);
        gbd.setInitMethodName("customInitMethod");
        gbd.setDestroyMethodName("customDestroyMethod");
        registry.registerBeanDefinition("phone", gbd);
    }

    private void registerBeanFactoryPostProcessor(BeanDefinitionRegistry registry) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(CustomBeanFactoryPostProcessor.class);
        registry.registerBeanDefinition("customBeanFactoryPostProcessor", gbd);
    }

    private void registerBeanPostProcessor(BeanDefinitionRegistry registry) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(CustomBeanPostProcessor.class);
        registry.registerBeanDefinition("customBeanPostProcessor", gbd);
    }
}
