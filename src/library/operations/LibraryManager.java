package library.operations;

import library.models.*;
import library.users.*;
import library.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManager implements LibraryOperations<Book> {
    private Map<String, Book> books = new HashMap<String, Book>();
    private Map<String, User> users = new HashMap<String, User>();

    public void addUser(String userId, User user) {
        users.put(userId, user);
    }

    public void addBook(String userId, Book book) throws UserNotFoundException, AccessDeniedException {
        User u = users.get(userId);
        if (u == null) throw new UserNotFoundException("Пользователь не найден: " + userId);
        if (!(u instanceof Librarian)) throw new AccessDeniedException("Доступ запрещён: добавлять может только Librarian");
        books.put(book.getId(), book);
    }

    public void removeBook(String userId, String bookId) throws UserNotFoundException, AccessDeniedException, BookNotFoundException {
        User u = users.get(userId);
        if (u == null) throw new UserNotFoundException("Пользователь не найден: " + userId);
        if (!(u instanceof Librarian)) throw new AccessDeniedException("Доступ запрещён: удалять может только Librarian");
        if (!books.containsKey(bookId)) throw new BookNotFoundException("Книга не найдена: " + bookId);
        books.remove(bookId);
    }

    public Book findBook(String userId, String title) throws UserNotFoundException, BookNotFoundException {
        User u = users.get(userId);
        if (u == null) throw new UserNotFoundException("Пользователь не найден: " + userId);
        for (Book b : books.values()) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        throw new BookNotFoundException("Книга с названием не найдена: " + title);
    }

    public List<Book> listBooks() {
        List<Book> list = new ArrayList<Book>();
        for (Book b : books.values()) {
            list.add(b);
        }
        return list;
    }

    public void borrowBook(String userId, String bookId) throws UserNotFoundException, BookNotFoundException {
        User u = users.get(userId);
        if (u == null) throw new UserNotFoundException("Пользователь не найден: " + userId);
        Book b = books.get(bookId);
        if (b == null) throw new BookNotFoundException("Книга не найдена: " + bookId);
        if (!b.isAvailable()) {
            // книга уже взята
            return;
        }
        b.setAvailable(false);
    }

    public void returnBook(String userId, String bookId) throws UserNotFoundException, BookNotFoundException {
        User u = users.get(userId);
        if (u == null) throw new UserNotFoundException("Пользователь не найден: " + userId);
        Book b = books.get(bookId);
        if (b == null) throw new BookNotFoundException("Книга не найдена: " + bookId);
        b.setAvailable(true);
    }
}
