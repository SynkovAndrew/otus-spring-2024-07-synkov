package otus.spring.homework3springboot.dao;

import otus.spring.homework3springboot.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
