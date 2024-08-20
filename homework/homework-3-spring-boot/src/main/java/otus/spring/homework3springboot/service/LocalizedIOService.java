package otus.spring.homework3springboot.service;

public interface LocalizedIOService extends IOService {

    void printLocalizedLine(String code);

    void printLocalizedFormattedLine(String code, Object ... arguments);

}
