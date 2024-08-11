package otus.spring.solution9DIP.services;

public interface ApplicationStopService {
    boolean isApplicationRunning();
    void stopApplication();
}
