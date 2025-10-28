package library.models;

import java.util.Comparator;

public abstract class Book { protected String id;
    protected String title;
    protected String author;
    protected int year;
    protected boolean available;

    public Book(String id, String title, String author, int year, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = available;
    }

    public abstract String getType();

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static Comparator<Book> TitleComparator = new Comparator<Book>() {
        public int compare(Book b1, Book b2) {
            return b1.title.compareToIgnoreCase(b2.title);
        }
    };

    @Override
    public String toString() {
        String avail = available ? "Доступна" : "Занята";
        return id + " - " + title + " (" + author + ", " + year + ") - " + getType() + " - " + avail;
    }
}
