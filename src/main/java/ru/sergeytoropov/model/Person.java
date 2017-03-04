package ru.sergeytoropov.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by sergeytoropov on 03.03.17.
 */
@NamedQueries({
        @NamedQuery(name = Person.PERSONS, query = "SELECT p FROM Person p"),
})
@Entity
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(columnNames = {"passport_seria", "passport_number"}, name = "person_unique_idx")})
public class Person extends BaseEntity {

    public static final String PERSONS = "Person.persons";

    @Column(name = "passport_seria", nullable = false)
    @NotEmpty
    protected Integer passortSeria;

    @Column(name = "passport_number", nullable = false)
    @NotEmpty
    protected Integer passortNumber;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "middle_name")
    protected String middleName;

    public Person() {
    }

    public Person(Person p) {
        this(p.id, p.passortSeria, p.passortNumber, p.lastName, p.firstName, p.middleName);
    }

    public Person(Integer id, Integer passortSeria, Integer passortNumber, String lastName, String firstName, String middleName) {
        super(id);
        this.passortSeria = passortSeria;
        this.passortNumber = passortNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public Integer getPassortSeria() {
        return passortSeria;
    }

    public void setPassortSeria(Integer passortSeria) {
        this.passortSeria = passortSeria;
    }

    public Integer getPassortNumber() {
        return passortNumber;
    }

    public void setPassortNumber(Integer passortNumber) {
        this.passortNumber = passortNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "Person (" +
                "id=" + id +
                ", passportSeria=" + passortSeria +
                ", passportNumber=" + passortNumber +
                ", lastName=" + lastName+
                ", firstName=" + firstName +
                ", middleName=" + middleName +
                ')';
    }
}
