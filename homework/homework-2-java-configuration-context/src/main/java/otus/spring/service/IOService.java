package otus.spring.service;

public interface IOService {

    void printLine(String line);

    void printFormattedLine(String line, Object ... arguments);

    String readLine();
}
