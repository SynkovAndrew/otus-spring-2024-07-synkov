package otus.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.spring.service.IOService;
import otus.spring.service.StreamsIOService;

@Configuration
public class IOServiceConfiguration {

    @Bean
    public IOService streamsIOService() {
        return new StreamsIOService(System.out, System.in);
    }
}
