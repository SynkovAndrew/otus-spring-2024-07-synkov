package otus.spring.module1lesson5springboot.config;

import java.util.Locale;

public class DefaultLocaleProvider implements LocaleProvider {

    private final Locale locale;

    public DefaultLocaleProvider(String localeTag) {
        this.locale = Locale.forLanguageTag(localeTag);
    }

    @Override
    public Locale getCurrent() {
        return locale;
    }
}
