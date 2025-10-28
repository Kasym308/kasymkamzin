package library.app;

import library.models.*;
import library.users.*;
import library.operations.*;
import library.exceptions.*;

import java.util.List;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryManager lib = new LibraryManager();

        for (int i = 1; i <= 5; i++) {
            String id = "L" + i;
            lib.addUser(id, new Librarian(id, "Librarian " + i));
        }

        for (int i = 1; i <= 5; i++) {
            String id = "R" + i;
            lib.addUser(id, new Reader(id, "Reader " + i));
        }

        for (int i = 1; i <= 5; i++) {
            PrintedBook pb = new PrintedBook("P" + i, "Printed Book " + i, "Author A", 2000 + i, true, 100 + i);
            try {
                lib.addBook("L1", pb);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 1; i <= 5; i++) {
            EBook eb = new EBook("E" + i, "EBook " + i, "Author B", 2010 + i, true, 1.5 + i);
            try {
                lib.addBook("L2", eb);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 1; i <= 10; i++) {
            String title = (i <= 5) ? "Printed Book " + i : "EBook " + (i - 5);
            try {
                Book found = lib.findBook("R1", title);
                System.out.println(found.toString());
            } catch (UserNotFoundException e) {
                System.out.println("UserNotFoundException: " + e.getMessage());
            } catch (BookNotFoundException e) {
                System.out.println("BookNotFoundException: " + e.getMessage());
            }
        }

        System.out.println("\n--- Симуляция ошибок ---");

        try {
            lib.findBook("X999", "Printed Book 1");
        } catch (UserNotFoundException e) {
            System.out.println("Случай 1 (UserNotFound): " + e.getMessage());
        } catch (BookNotFoundException e) {
            System.out.println("BookNotFound (не ожидалось): " + e.getMessage());
        }

        try {
            lib.removeBook("R1", "P1");
        } catch (UserNotFoundException e) {
            System.out.println("UserNotFoundException: " + e.getMessage());
        } catch (AccessDeniedException e) {
            System.out.println("Случай 2 (AccessDenied): " + e.getMessage());
        } catch (BookNotFoundException e) {
            System.out.println("BookNotFoundException: " + e.getMessage());
        }

        try {
            lib.removeBook("L1", "UNKNOWN_ID");
        } catch (UserNotFoundException e) {
            System.out.println("UserNotFoundException: " + e.getMessage());
        } catch (AccessDeniedException e) {
            System.out.println("AccessDeniedException: " + e.getMessage());
        } catch (BookNotFoundException e) {
            System.out.println("Случай 3 (BookNotFound): " + e.getMessage());
        }

        try {
            lib.addBook("R1", new PrintedBook("P100", "New Book", "Author X", 2025, true, 123));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (AccessDeniedException e) {
            System.out.println("Случай 4 (AccessDenied при добавлении): " + e.getMessage());
        }

        try {
            lib.borrowBook("R2", "P1");
            System.out.println("R2 забрал P1");
            lib.borrowBook("R3", "P1");
            System.out.println("R3 попытался забрать P1 (должно быть занято)");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            lib.returnBook("R2", "P1");
            System.out.println("R2 вернул P1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Book> all = lib.listBooks();
        System.out.println("\n--- Список всех книг ---");
        for (Book b : all) {
            System.out.println(b);
        }
    }
}
