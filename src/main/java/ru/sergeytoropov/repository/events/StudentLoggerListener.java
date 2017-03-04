package ru.sergeytoropov.repository.events;

import ru.sergeytoropov.model.Student;

import javax.persistence.PostRemove;

/**
 * Created by sergeytoropov on 04.03.17.
 */
public class StudentLoggerListener {

    @PostRemove
    private void methodInvokedBeforeRemove(Student student) {
        System.out.println("\nR E M O V I N G   S T U D E N T   [" + student + "]\n");
    }

}
