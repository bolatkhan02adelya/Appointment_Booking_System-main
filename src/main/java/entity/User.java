package entity;

public class User {
    private int id;
    private String name;
    private String surname;
    private boolean gender ;

    public User(String name, String surname, boolean gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public User(int id, String name, String surname, boolean gender) {
        this(name, surname, gender);
        this.id = id;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public boolean getGender() { return gender; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', surname='" + surname + "', gender=" + (gender ? "Male" : "Female") + "}";
    }
}
