package otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import otus.spring.configuration.QuestionFileNameProvider;
import otus.spring.dao.dto.QuestionDto;
import otus.spring.domain.Question;
import otus.spring.exception.FailedToReadQuestionsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class QuestionCsvDao implements QuestionDao {
    private final QuestionFileNameProvider questionFileNameProvider;

    @Override
    public List<Question> findAll() {
        try (Reader reader = getFilePath(questionFileNameProvider.getFileName())) {
            return new CsvToBeanBuilder<QuestionDto>(reader)
                    .withType(QuestionDto.class)
                    .withSeparator(';')
                    .build()
                    .parse()
                    .stream()
                    .map(QuestionDto::toDomain)
                    .toList();
        } catch (IOException exception) {
            throw new FailedToReadQuestionsException(
                    "Failed to read questions from " + questionFileNameProvider.getFileName(),
                    exception
            );
        }
    }

    private BufferedReader getFilePath(String fileName) {
        return Optional.ofNullable(ClassLoader.getSystemResourceAsStream(fileName))
                .map(InputStreamReader::new)
                .map(BufferedReader::new)
                .orElseThrow(() -> new FailedToReadQuestionsException("File " + fileName + " not found"));
    }
}
