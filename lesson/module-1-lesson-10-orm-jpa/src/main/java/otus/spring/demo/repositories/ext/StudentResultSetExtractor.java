package otus.spring.demo.repositories.ext;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import otus.spring.demo.models.Avatar;
import otus.spring.demo.models.EMail;
import otus.spring.demo.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentResultSetExtractor implements ResultSetExtractor<Map<Long, Student>> {
    @Override
    public Map<Long, Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Student> students = new HashMap<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            Student student = students.get(id);
            if (student == null) {
                student = new Student(id, rs.getString("name"),
                        new Avatar(rs.getLong("avatar_id"), rs.getString("photo_url")),
                        new ArrayList<>(), new ArrayList<>());
                students.put(student.getId(), student);
            }

            student.getEmails()
                    .add(new EMail(rs.getLong("email_id"), rs.getString("email")));
        }
        return students;
    }
}
