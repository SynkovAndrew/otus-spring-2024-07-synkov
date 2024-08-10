package otus.spring.solution10OCPandISP.services;


import otus.spring.solution10OCPandISP.model.Note;

public interface NoteConverter {
    String convertNoteToString(int noteNumber, Note note);
}
