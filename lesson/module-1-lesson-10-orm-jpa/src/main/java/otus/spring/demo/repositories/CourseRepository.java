package otus.spring.demo.repositories;


import otus.spring.demo.models.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAllUsed();
}
