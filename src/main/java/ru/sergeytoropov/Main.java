package ru.sergeytoropov;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import ru.sergeytoropov.model.Person;
import ru.sergeytoropov.model.RecordBook;
import ru.sergeytoropov.model.Student;
import ru.sergeytoropov.repository.StudentCriteriaRepository;
import ru.sergeytoropov.repository.StudentHQLRepository;
import ru.sergeytoropov.repository.jpa.JpaPersonHQLRepositoryImpl;
import ru.sergeytoropov.repository.jpa.JpaRecordBookHQLRepositoryImpl;

import java.util.List;

/**
 * Created by sergeytoropov on 03.03.17.
 */
@Transactional(readOnly = true)
public class Main {
    public static void printBeans(ConfigurableApplicationContext appCtx) {
        System.out.println("Beans:");
        for (String beanName: appCtx.getBeanDefinitionNames()) {
            System.out.println("    " + beanName);
        }
    }

    public static void checkDatabase(ConfigurableApplicationContext appCtx) {
        StudentHQLRepository studentHQL = appCtx.getBean(StudentHQLRepository.class);
        List<Student> students = studentHQL.getAll();

        JpaPersonHQLRepositoryImpl personHQL = appCtx.getBean(JpaPersonHQLRepositoryImpl.class);
        List<Person> persons = personHQL.getAll();

        JpaRecordBookHQLRepositoryImpl recordBookHQL = appCtx.getBean(JpaRecordBookHQLRepositoryImpl.class);
        List<RecordBook> recordBooks = recordBookHQL.getAll();

        System.out.println("\n\nDatabase:\n");
        System.out.println("Table student");
        students.stream().forEach(System.out::println);
        System.out.println("Table person");
        persons.stream().forEach(System.out::println);
        System.out.println("Table record book");
        recordBooks.stream().forEach(System.out::println);
        System.out.println("\n\nEnd!\n");
    }

    public static void print(String message, List<Student> listHQL, List<Student> listCriteria) {
        System.out.println("\n" + message);
        System.out.println("HQL");
        listHQL.stream().forEach(System.out::println);
        System.out.println("\nCriteria");
        listCriteria.stream().forEach(System.out::println);
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("classpath:spring/spring-db.xml");

        //printBeans(appCtx);

        StudentHQLRepository studentHQL = appCtx.getBean(StudentHQLRepository.class);
        StudentCriteriaRepository studentCriteria = appCtx.getBean(StudentCriteriaRepository.class);

        List<Student> listHQL = studentHQL.getWithNameIncludeLetterA();
        List<Student> listCriteria = studentCriteria.getWithNameIncludeLetterA();

        print("Student with name include letter a", listHQL, listCriteria);

        listHQL = studentHQL.getWithoutRecordBook();
        listCriteria = studentCriteria.getWithoutRecordBook();

        print("Student without record book", listHQL, listCriteria);

        studentHQL.deleteAll();

        //checkDatabase(appCtx);

        appCtx.close();
    }
}
