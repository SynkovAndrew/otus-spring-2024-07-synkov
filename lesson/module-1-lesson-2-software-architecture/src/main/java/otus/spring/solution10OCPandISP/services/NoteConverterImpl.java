package otus.spring.solution10OCPandISP.services;

import otus.spring.solution10OCPandISP.config.DateTimeFormatProvider;
import otus.spring.solution10OCPandISP.model.Note;

import java.time.format.DateTimeFormatter;

public class NoteConverterImpl implements NoteConverter {
    private final DateTimeFormatProvider dateTimeFormatProvider;

    public NoteConverterImpl(DateTimeFormatProvider dateTimeFormatProvider) {
        this.dateTimeFormatProvider = dateTimeFormatProvider;
    }

    @Override
    public String convertNoteToString(int noteNumber, Note note) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatProvider.getDateTimeFormat());
        return noteNumber + " | " + dateTimeFormatter.format(note.getCreationTime()) + " | " + note.getText();
    }
}
