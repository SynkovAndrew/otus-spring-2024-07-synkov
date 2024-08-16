package otus.spring.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainProxy {

    public static void main(String[] args) {
        var aspect = new LoggingAspectSimple();
        Getter getter = new SimpleGetter();

        Getter getterProxyJdk = (Getter) Proxy.newProxyInstance(
                MainProxy.class.getClassLoader(),
                getter.getClass().getInterfaces(),
                (proxy, method, arguments) -> {
                    aspect.logBefore(getter.getClass(), proxy.getClass(), method, arguments);

                    return method.invoke(getter, arguments);
                }
        );

        getterProxyJdk.getNumber();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleGetter.class);
        enhancer.setCallback((InvocationHandler) (proxy, method, arguments) -> {
            aspect.logBefore(getter.getClass(), proxy.getClass(), method, arguments);

            return method.invoke(getter, arguments);
        });

        SimpleGetter cglibSimpleGetter = (SimpleGetter) enhancer.create();
        cglibSimpleGetter.getNumber();
    }

    private static class LoggingAspectSimple {
        public void logBefore(Class<?> originalClass, Class<?> proxyClass, Method method, Object[] methodArgs) {
            System.out.println("Прокси : " + proxyClass.getName());
            System.out.println("Класс : " + originalClass.getName());
        }
    }
}
