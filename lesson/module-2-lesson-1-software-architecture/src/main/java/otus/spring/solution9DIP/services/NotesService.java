package otus.spring.solution9DIP.services;


import otus.spring.solution9DIP.model.Note;

import java.util.List;

public interface NotesService {
    List<Note> getAll();

    void save(Note note);

    void remove(String id);
}
