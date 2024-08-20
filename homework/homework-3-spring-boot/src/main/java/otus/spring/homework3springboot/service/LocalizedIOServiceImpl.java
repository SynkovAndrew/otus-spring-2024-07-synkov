package otus.spring.homework3springboot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LocalizedIOServiceImpl implements LocalizedIOService {
    private final IOService ioService;

    private final LocalizedMessageService localizedMessageService;

    public LocalizedIOServiceImpl(
            @Qualifier("streamsIOService") IOService ioService,
            LocalizedMessageService localizedMessageService
    ) {
        this.ioService = ioService;
        this.localizedMessageService = localizedMessageService;
    }


    @Override
    public void printLocalizedLine(String code) {
        ioService.printLine(localizedMessageService.getMessage(code));
    }

    @Override
    public void printLocalizedFormattedLine(String code, Object... arguments) {
        ioService.printLine(localizedMessageService.getMessage(code, arguments));
    }

    @Override
    public void printLine(String line) {
        ioService.printLine(line);
    }

    @Override
    public void printFormattedLine(String line, Object... arguments) {
        ioService.printFormattedLine(line, arguments);
    }

    @Override
    public String readLine() {
        return ioService.readLine();
    }

    @Override
    public Integer readInteger() {
        return ioService.readInteger();
    }
}
