package ru.sergeytoropov.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sergeytoropov.model.Student;
import ru.sergeytoropov.repository.StudentHQLRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sergeytoropov on 04.03.17.
 */
@Repository
@Transactional(readOnly = true)
public class JpaStudentHQLRepositoryImpl implements StudentHQLRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Student> getAll() {
        return em.createNamedQuery(Student.STUDENTS, Student.class).getResultList();
    }

    @Override
    public List<Student> getWithNameIncludeLetterA() {
        return em.createNamedQuery(Student.STUDENTS_NAME_INCLUDE_LETTER_A, Student.class).getResultList();
    }

    @Override
    public List<Student> getWithoutRecordBook() {
        return em.createNamedQuery(Student.STUDENTS_HAVE_NOT_RECORD_BOOK, Student.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteAll() {
        getAll().stream().forEach(em::remove);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Student.DELETE_STUDENT_BY_ID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }
}
