package ru.sergeytoropov.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sergeytoropov.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sergeytoropov on 04.03.17.
 */
@Repository
@Transactional(readOnly = true)
public class JpaPersonHQLRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Person> getAll() {
        return em.createNamedQuery(Person.PERSONS, Person.class).getResultList();
    }
}
