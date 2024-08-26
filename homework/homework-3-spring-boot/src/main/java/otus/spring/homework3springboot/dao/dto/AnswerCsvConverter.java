package otus.spring.homework3springboot.dao.dto;

import com.opencsv.bean.AbstractCsvConverter;

public class AnswerCsvConverter extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String s) {
        var parts = s.split("%");
        return new AnswerDto(Integer.valueOf(parts[0]), parts[1], Boolean.parseBoolean(parts[2]));
    }
}
