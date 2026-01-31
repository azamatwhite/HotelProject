package repositories.interfaces;
import models.User;

public interface IUserRepository {
    User createUser(User user);
    User getUserByPhone(String phone); // Для пункта 9
    User getUserById(int id);
}