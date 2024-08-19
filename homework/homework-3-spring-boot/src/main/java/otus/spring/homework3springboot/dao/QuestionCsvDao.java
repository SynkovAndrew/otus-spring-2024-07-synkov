package otus.spring.homework3springboot.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.homework3springboot.configuration.QuestionFileNameProvider;
import otus.spring.homework3springboot.dao.dto.QuestionDto;
import otus.spring.homework3springboot.domain.Question;
import otus.spring.homework3springboot.exception.QuestionReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@Component
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
            throw new QuestionReadException(
                    "Failed to read questions from " + questionFileNameProvider.getFileName(),
                    exception
            );
        }
    }

    private BufferedReader getFilePath(String fileName) {
        return Optional.ofNullable(ClassLoader.getSystemResourceAsStream(fileName))
                .map(InputStreamReader::new)
                .map(BufferedReader::new)
                .orElseThrow(() -> new QuestionReadException("File " + fileName + " not found"));
    }
}
