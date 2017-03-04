package ru.sergeytoropov.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sergeytoropov.model.Person;
import ru.sergeytoropov.model.Student;
import ru.sergeytoropov.repository.StudentCriteriaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by sergeytoropov on 04.03.17.
 */
@Repository
@Transactional(readOnly = true)
public class JpaStudentCriteriaRepositoryImpl implements StudentCriteriaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Student> getAll() {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = b.createQuery(Student.class);

        Root<Student> tableStudent = criteria.from(Student.class);
        criteria.select(tableStudent);

        return em.createQuery(criteria).getResultList();
    }

    @Override
    public List<Student> getWithNameIncludeLetterA() {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = b.createQuery(Student.class);

        Root<Student> tableStudent = criteria.from(Student.class);
        Join<Student, Person> innerJoinTablePerson= tableStudent.join("person");

        criteria
                .select(tableStudent)
                .where(
                        b.like(
                                b.upper(
                                        b.concat(innerJoinTablePerson.get("lastName"),
                                                b.concat(innerJoinTablePerson.get("firstName"), innerJoinTablePerson.get("middleName")
                                                ))), "%А%"));

        return em.createQuery(criteria).getResultList();
    }

    @Override
    public List<Student> getWithoutRecordBook() {
        CriteriaBuilder b = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = b.createQuery(Student.class);

        Root<Student> tableStudent = criteria.from(Student.class);

        criteria
                .select(tableStudent)
                .where(b.isNull(tableStudent.get("recordBook")));

        return em.createQuery(criteria).getResultList();
    }
}
