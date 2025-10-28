package library.users;

public abstract class User {
    protected String id;
    protected String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void showRole();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
