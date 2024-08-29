package otus.spring.homework4springshell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import otus.spring.homework4springshell.configuration.LocaleProvider;

@Component
@RequiredArgsConstructor
public class LocalizedMessageServiceImpl implements LocalizedMessageService {
    private final LocaleProvider localeProvider;

    private final MessageSource messageSource;

    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, localeProvider.getLocale());
    }
}
