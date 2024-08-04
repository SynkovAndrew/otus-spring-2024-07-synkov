package otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import otus.spring.configuration.QuestionFileNameProvider;
import otus.spring.dao.dto.QuestionDto;
import otus.spring.domain.Question;
import otus.spring.exception.FailedToReadQuestionsException;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
public class QuestionCsvDao implements QuestionDao {
    private final QuestionFileNameProvider questionFileNameProvider;

    @Override
    public List<Question> findAll() {
        try (Reader reader = Files.newBufferedReader(getFilePath(questionFileNameProvider.getFileName()))) {
            return new CsvToBeanBuilder<QuestionDto>(reader)
                    .withType(QuestionDto.class)
                    .withSeparator(';')
                    .build()
                    .parse()
                    .stream()
                    .map(QuestionDto::toDomain)
                    .toList();
        } catch (IOException | URISyntaxException exception) {
            throw new FailedToReadQuestionsException(
                    "Failed to read questions from " + questionFileNameProvider.getFileName(),
                    exception
            );
        }
    }

    private Path getFilePath(String fileName) throws URISyntaxException {
       return Paths.get(ClassLoader.getSystemResource(fileName).toURI());
    }
}
