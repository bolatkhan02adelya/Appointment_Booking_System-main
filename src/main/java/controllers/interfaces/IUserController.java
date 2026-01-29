package controllers.interfaces;

public interface IUserController {
    String createUser(String name, String surname, boolean gender);
    String getUserById(int id);
    String getAllUsers();
}
