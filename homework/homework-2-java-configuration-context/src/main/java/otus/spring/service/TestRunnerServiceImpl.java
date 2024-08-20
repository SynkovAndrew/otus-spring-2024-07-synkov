package otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.aop.ExecutionTimeLogger;

@Component
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {
    private final TestService testService;

    @Override
    @ExecutionTimeLogger
    public void run() {
        testService.executeTest();
    }
}
