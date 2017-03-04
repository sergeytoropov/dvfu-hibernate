package ru.sergeytoropov.repository;

/**
 * Created by sergeytoropov on 04.03.17.
 */
public interface StudentHQLRepository extends StudentRepository {
    void deleteAll();

    boolean delete(int id);
}
