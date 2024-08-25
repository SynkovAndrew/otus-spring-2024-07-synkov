package otus.spring.homework4springshell.dao;

import otus.spring.homework4springshell.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
