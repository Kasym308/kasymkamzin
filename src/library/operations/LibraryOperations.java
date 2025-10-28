package library.operations;

import library.models.Book;
import library.users.User;
import library.exceptions.*;

import java.util.List;

public interface LibraryOperations<T extends Book> {
    void addUser(String userId, User user);
    void addBook(String userId, T book) throws UserNotFoundException, AccessDeniedException;
    void removeBook(String userId, String bookId) throws UserNotFoundException, AccessDeniedException, BookNotFoundException;
    T findBook(String userId, String title) throws UserNotFoundException, BookNotFoundException;
    List<T> listBooks();
    void borrowBook(String userId, String bookId) throws UserNotFoundException, BookNotFoundException;
    void returnBook(String userId, String bookId) throws UserNotFoundException, BookNotFoundException;
}
