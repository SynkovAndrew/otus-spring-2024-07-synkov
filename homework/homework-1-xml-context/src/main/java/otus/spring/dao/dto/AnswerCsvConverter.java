package otus.spring.dao.dto;

import com.opencsv.bean.AbstractCsvConverter;

public class AnswerCsvConverter extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String s) {
        var parts = s.split("%");
        return new AnswerDto(parts[0], Boolean.parseBoolean(parts[1]));
    }
}
