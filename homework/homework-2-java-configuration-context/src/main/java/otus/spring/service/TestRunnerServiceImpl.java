package otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {
    private final TestService testService;

    @Override
    public void run() {
        testService.executeTest();
    }
}
