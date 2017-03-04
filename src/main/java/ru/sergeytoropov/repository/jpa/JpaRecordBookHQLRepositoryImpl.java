package ru.sergeytoropov.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sergeytoropov.model.RecordBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sergeytoropov on 04.03.17.
 */
@Repository
@Transactional(readOnly = true)
public class JpaRecordBookHQLRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<RecordBook> getAll() {
        return em.createNamedQuery(RecordBook.RECORD_BOOKS, RecordBook.class).getResultList();
    }
}
