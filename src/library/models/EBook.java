package library.models;

public class EBook extends Book{
    private double fileSizeMB;

    public EBook(String id, String title, String author, int year, boolean available, double fileSizeMB) {
        super(id, title, author, year, available);
        this.fileSizeMB = fileSizeMB;
    }

    public double getFileSizeMB() {
        return fileSizeMB;
    }

    @Override
    public String getType() {
        return "EBook";
    }
}
