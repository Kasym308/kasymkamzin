package library.users;

public class Reader extends User {
    public Reader(String id, String name) {
        super(id, name);
    }

    @Override
    public void showRole() {
        System.out.println(name + " - Reader");
    }
}
