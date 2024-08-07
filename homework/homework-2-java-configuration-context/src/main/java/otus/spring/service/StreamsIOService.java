package otus.spring.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class StreamsIOService implements IOService {
    private final PrintStream printStream;
    private final Scanner scanner;

    public StreamsIOService(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public void printLine(String line) {
        printStream.println(line);
    }

    @Override
    public void printFormattedLine(String line, Object... arguments) {
        printStream.printf(line, arguments);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
