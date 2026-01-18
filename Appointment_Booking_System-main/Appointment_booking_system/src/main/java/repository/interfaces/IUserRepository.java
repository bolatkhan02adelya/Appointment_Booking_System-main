package repository.interfaces;

import entity.User;
import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
}