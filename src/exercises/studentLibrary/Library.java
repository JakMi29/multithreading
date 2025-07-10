package exercises.studentLibrary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Library {

    public static void main(String[] args) {
        Student[] students = new Student[Constants.NUMBER_OF_STUDENTS];
        Book[] books = new Book[Constants.NUMBER_OF_BOOKS];

        for (int i = 0; i < books.length; i++) {
            books[i] = new Book(i);
        }

        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i, books);
        }

        ExecutorService service = Executors.newFixedThreadPool(students.length);

        for (Student student : students) {
            service.execute(student);
        }
    }
}
