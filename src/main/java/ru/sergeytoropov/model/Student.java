package ru.sergeytoropov.model;

import ru.sergeytoropov.repository.events.StudentLoggerListener;

import javax.persistence.*;

/**
 * Created by sergeytoropov on 03.03.17.
 */
@NamedQueries({
        @NamedQuery(
                name = Student.STUDENTS,
                query = "SELECT s FROM Student s"),
        @NamedQuery(
                name = Student.STUDENTS_NAME_INCLUDE_LETTER_A,
                query = "SELECT s FROM Student s INNER JOIN s.person sp WHERE UPPER(CONCAT(sp.lastName, sp.firstName, sp.middleName)) LIKE '%А%'"),
        @NamedQuery(
                name = Student.STUDENTS_HAVE_NOT_RECORD_BOOK,
                query = "SELECT s FROM Student s WHERE s.recordBook IS NULL"),
        @NamedQuery(
                name = Student.DELETE_STUDENT_BY_ID,
                query = "DELETE FROM Student s WHERE s.id=:id"),
})
@Entity
@EntityListeners(StudentLoggerListener.class)
@Table(name = "student")
public class Student extends BaseEntity {
    public static final String STUDENTS = "Student.students";
    public static final String STUDENTS_NAME_INCLUDE_LETTER_A = "Student.studentsNameIncludeLetterA";
    public static final String STUDENTS_HAVE_NOT_RECORD_BOOK = "Student.studentsHaveNotRecordBook";
    public static final String DELETE_STUDENT_BY_ID = "Student.deleteStudentById";

    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "record_book")
    private RecordBook recordBook;

    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "person", nullable = false)
    private Person person;

    @Column(name = "group_name")
    protected String groupName;

    public Student() {
    }

    public Student(Student s) {
        this(s.id, s.groupName);
    }

    public Student(Integer id, String groupName) {
        super(id);
        this.groupName = groupName;
    }

    public RecordBook getRecordBook() {
        return recordBook;
    }

    public void setRecordBook(RecordBook recordBook) {
        this.recordBook = recordBook;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Student (" +
                "id=" + id +
                ", groupName=" + groupName +
                ')' + " " + person + " " + recordBook;
    }
}

