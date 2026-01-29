package controllers;

import controllers.interfaces.IUserController;
import entity.User;
import repository.interfaces.IUserRepository;
import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser(String name, String surname, boolean gender) {
        User user = new User(name, surname, gender);
        boolean created = repo.createUser(user);

        return (created) ? "User was created!" : "User creation failed!";
    }

    @Override
    public String getUserById(int id) {
        User user = repo.getUserById(id);
        return (user == null) ? "User not found!" : user.toString();
    }

    @Override
    public String getAllUsers() {
        List<User> users = repo.getAllUsers();
        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.getName()).append(" ").append(user.getSurname()).append("\n");
        }
        return response.toString();
    }
}
