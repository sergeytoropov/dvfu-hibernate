package ru.sergeytoropov.repository;

import ru.sergeytoropov.model.Student;

import java.util.List;

/**
 * Created by sergeytoropov on 04.03.17.
 */
public interface StudentRepository {
    List<Student> getAll();

    List<Student> getWithNameIncludeLetterA();

    List<Student> getWithoutRecordBook();
}
