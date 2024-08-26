package otus.spring.homework3springboot.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import otus.spring.homework3springboot.configuration.LocaleProvider;
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

    private final LocaleProvider localeProvider;

    @Override
    public List<Question> findAll() {
        try (Reader reader = getFilePath(getLocalizedFileName())) {
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
                    "Failed to read questions from " + getLocalizedFileName(),
                    exception
            );
        }
    }

    private String getLocalizedFileName() {
        return questionFileNameProvider.getFileName(localeProvider.getLocale());
    }

    private BufferedReader getFilePath(String fileName) {
        return Optional.ofNullable(getClass().getResourceAsStream("/" + fileName))
                .map(InputStreamReader::new)
                .map(BufferedReader::new)
                .orElseThrow(() -> new QuestionReadException("File " + fileName + " not found"));
    }
}
