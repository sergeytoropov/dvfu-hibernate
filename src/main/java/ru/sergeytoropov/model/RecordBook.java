package ru.sergeytoropov.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by sergeytoropov on 03.03.17.
 */
@NamedQueries({
        @NamedQuery(name = RecordBook.RECORD_BOOKS, query = "SELECT rb FROM RecordBook rb"),
})
@Entity
@Table(name = "record_book", uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "record_book_unique_idx")})
public class RecordBook extends BaseEntity {

    public static final String RECORD_BOOKS = "RecordBook.recordBooks";

    @Column(name = "code", nullable = false)
    @NotEmpty
    protected Integer code;

    public RecordBook() {
    }

    public RecordBook(RecordBook rb) {
        this(rb.id, rb.code);
    }

    public RecordBook(Integer id, Integer code) {
        super(id);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RecordBook (" +
                "id=" + id +
                ", code=" + code +
                ')';
    }
}

