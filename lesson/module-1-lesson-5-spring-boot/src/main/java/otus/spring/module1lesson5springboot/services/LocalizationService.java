package otus.spring.module1lesson5springboot.services;

public interface LocalizationService {
    String getMessage(String key, Object ...args);
}
