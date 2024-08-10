package otus.spring.solution9DIP.services;

import otus.spring.solution9DIP.model.Note;

public interface NoteConverter {
    String convertNoteToString(int noteNumber, Note note);
}
