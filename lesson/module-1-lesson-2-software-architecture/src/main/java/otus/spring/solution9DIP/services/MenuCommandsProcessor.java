package otus.spring.solution9DIP.services;

public interface MenuCommandsProcessor {
    void showAllNotes();

    void addNewNote();

    void updateNote();

    void deleteNote();

    void stopApplication();
}
