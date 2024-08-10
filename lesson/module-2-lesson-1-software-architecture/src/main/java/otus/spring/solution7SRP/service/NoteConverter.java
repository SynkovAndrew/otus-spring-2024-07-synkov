package otus.spring.solution7SRP.service;

import otus.spring.solution7SRP.model.Note;

public class NoteConverter {
    public String convertNoteToString(int noteNumber, Note note) {
        return noteNumber + " | " + note.getCreationTime() + " | " + note.getText();
    }
}