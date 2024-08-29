package otus.spring.homework4springshell.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.spring.homework4springshell.service.IOService;
import otus.spring.homework4springshell.service.StreamsIOService;

@Configuration
public class IOServiceConfiguration {

    @Bean
    public IOService streamsIOService() {
        return new StreamsIOService(System.out, System.in);
    }
}
