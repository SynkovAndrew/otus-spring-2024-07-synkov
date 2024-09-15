package otus.spring.repositories;

import jakarta.persistence.EntityGraph;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.models.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;


// @Transactional должна стоять на методе сервиса.
// Причем, если метод не подразумевает изменения данных в БД то категорически желательно
// выставить у аннотации параметр readOnly в true.
// Но это только упражнение и транзакции мы пока не проходили.
// Поэтому, для упрощения, пока вешаем над классом репозитория
@Transactional
@Repository
public class JpaStudentRepository implements StudentRepository {

    @PersistenceContext
    private final EntityManager em;

    public JpaStudentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student save(Student student) {
        if (student.getId() == 0) {
            em.persist(student);
            return student;
        }
        return em.merge(student);
    }

    @Override
    public Optional<Student> findById(long id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    @Override
    public List<Student> findAll() {
        return em.createQuery("select s from Student s left join fetch s.emails", Student.class)
                .setHint(FETCH.getKey(), em.getEntityGraph("student-avatars-entity-graph"))
                .getResultList();
    }

    @Override
    public List<Student> findByName(String name) {
        return em.createQuery("select s from Student s where s.name = :name", Student.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        em.createQuery("update Student s set s.name = :name where s.id = :id")
                .setParameter("name", name)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        em.createQuery("delete from Student s where s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
