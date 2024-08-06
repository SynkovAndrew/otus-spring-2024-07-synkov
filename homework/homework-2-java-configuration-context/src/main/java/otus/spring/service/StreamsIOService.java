package otus.spring.service;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@RequiredArgsConstructor
public class StreamsIOService implements IOService {
    private final PrintStream printStream;

    private final Scanner scanner = new Scanner(System.in);

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
