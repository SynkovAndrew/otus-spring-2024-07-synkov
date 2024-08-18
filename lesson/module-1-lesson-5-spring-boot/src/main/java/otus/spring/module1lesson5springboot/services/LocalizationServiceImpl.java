package otus.spring.module1lesson5springboot.services;

import org.springframework.context.MessageSource;
import otus.spring.module1lesson5springboot.config.LocaleProvider;

public class LocalizationServiceImpl implements LocalizationService {

    private final LocaleProvider localeProvider;
    private final MessageSource messageSource;

    public LocalizationServiceImpl(LocaleProvider localeProvider, MessageSource messageSource) {
        this.localeProvider = localeProvider;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, localeProvider.getCurrent());
    }
}
