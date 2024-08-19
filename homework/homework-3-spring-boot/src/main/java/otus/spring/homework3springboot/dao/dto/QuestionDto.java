package otus.spring.homework3springboot.dao.dto;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import otus.spring.homework3springboot.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDto {

    @CsvBindByPosition(position = 0)
    private String value;

    @CsvBindAndSplitByPosition(
            position = 1,
            collectionType = ArrayList.class,
            elementType = AnswerDto.class,
            converter = AnswerCsvConverter.class,
            splitOn = ","
    )
    private List<AnswerDto> answers;

    public Question toDomain() {
        return new Question(
                value,
                answers.stream()
                        .map(AnswerDto::toDomain)
                        .toList()
        );
    }
}
