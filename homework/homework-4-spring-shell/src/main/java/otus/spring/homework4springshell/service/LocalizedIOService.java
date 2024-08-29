package otus.spring.homework4springshell.service;

public interface LocalizedIOService extends IOService {

    void printLocalizedLine(String code);

    void printLocalizedFormattedLine(String code, Object ... arguments);

}
