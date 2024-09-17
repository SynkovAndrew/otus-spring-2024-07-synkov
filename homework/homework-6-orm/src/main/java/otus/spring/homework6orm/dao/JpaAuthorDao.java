package otus.spring.homework6orm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import otus.spring.homework6orm.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaAuthorDao implements AuthorDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Author create(Author author) {
        entityManager.persist(author);

        return author;
    }

    @Override
    public Author findById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id))
                .orElseThrow(() -> new DaoException.EntityNotFound("Author(id=" + id + ") is not found"));
    }

    @Override
    public List<Author> findAll() {
        return entityManager.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author update(Author author) {

//        if (result == 0) {
//            throw new DaoException.EntityNotFound("Author(id=" + author.id() + ") is not found");
//        }

        return entityManager.merge(author);
    }

    @Override
    public Author delete(Author author) {



   /*     if (result == 0) {
            throw new DaoException.EntityNotFound("Author(id=" + author.id() + ") is not found");
        }*/
        entityManager.createQuery("delete from Author a where a.id = :id")
                .setParameter("id", author.getId())
                .executeUpdate();
        return author;
    }
}
