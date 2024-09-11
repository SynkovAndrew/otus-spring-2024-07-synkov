package otus.spring.homework5jdbc.domain;

import java.util.List;

public record Book(Long id, String title, List<Author> authors, Genre genre) {
}
