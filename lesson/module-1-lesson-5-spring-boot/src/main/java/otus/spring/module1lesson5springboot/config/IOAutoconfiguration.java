package otus.spring.module1lesson5springboot.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.spring.module1lesson5springboot.services.IOService;
import otus.spring.module1lesson5springboot.services.LocalizationService;
import otus.spring.module1lesson5springboot.services.LocalizationServiceImpl;
import otus.spring.module1lesson5springboot.services.StreamsIOService;

@ConditionalOnProperty(value = "otus.custom.io-enabled", havingValue = "true", matchIfMissing = false)
@Configuration
public class IOAutoconfiguration {

    @ConditionalOnMissingBean(IOService.class)
    @Bean
    public IOService ioService() {
        return new StreamsIOService(System.out, System.in);
    }

    @ConditionalOnMissingBean(LocaleProvider.class)
    @Bean
    public LocaleProvider localeProvider(@Value("${otus.custom.locale}") String localeTag) {
        return new DefaultLocaleProvider(localeTag);
    }

    @ConditionalOnMissingBean(LocalizationService.class)
    @ConditionalOnBean(MessageSource.class)
    @Bean
    public LocalizationService localizationService(LocaleProvider localeProvider, MessageSource messageSource) {
        return new LocalizationServiceImpl(localeProvider, messageSource);
    }
}
