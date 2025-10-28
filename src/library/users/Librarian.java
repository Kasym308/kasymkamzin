package library.users;

public class Librarian extends User {
    public Librarian(String id, String name) {
        super(id, name);
    }

    @Override
    public void showRole() {
        System.out.println(name + " - Librarian");
    }
}
