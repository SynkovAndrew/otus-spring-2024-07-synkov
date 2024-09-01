package otus.spring.exersice_1_solution.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Person {
    private final long id;
    private final String name;
}
