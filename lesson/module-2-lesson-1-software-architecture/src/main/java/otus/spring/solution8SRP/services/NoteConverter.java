package otus.spring.solution8SRP.services;


import otus.spring.solution8SRP.model.Note;

public class NoteConverter {
    public String convertNoteToString(int noteNumber, Note note) {
        return noteNumber + " | " + note.getCreationTime() + " | " + note.getText();
    }
}
