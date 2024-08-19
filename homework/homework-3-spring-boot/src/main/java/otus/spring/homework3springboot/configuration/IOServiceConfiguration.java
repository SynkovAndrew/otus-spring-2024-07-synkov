package otus.spring.homework3springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.spring.homework3springboot.service.IOService;
import otus.spring.homework3springboot.service.StreamsIOService;

@Configuration
public class IOServiceConfiguration {

    @Bean
    public IOService streamsIOService() {
        return new StreamsIOService(System.out, System.in);
    }
}
