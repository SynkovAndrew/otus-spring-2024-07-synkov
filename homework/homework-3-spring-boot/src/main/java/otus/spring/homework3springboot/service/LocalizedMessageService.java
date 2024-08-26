package otus.spring.homework3springboot.service;

public interface LocalizedMessageService {

    String getMessage(String code, Object... args);
}
