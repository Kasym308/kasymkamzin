package library.models;

public class PrintedBook extends Book{
    private int pages;

    public PrintedBook(String id, String title, String author, int year, boolean available, int pages) {
        super(id, title, author, year, available);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String getType() {
        return "Printed";
    }
}
