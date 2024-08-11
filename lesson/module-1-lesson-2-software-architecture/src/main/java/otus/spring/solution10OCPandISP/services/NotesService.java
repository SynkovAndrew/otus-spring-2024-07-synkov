package otus.spring.solution10OCPandISP.services;


import otus.spring.solution10OCPandISP.model.Note;

import java.util.List;

public interface NotesService {
    List<Note> getAll();

    void save(Note note);

    void remove(String id);
}
