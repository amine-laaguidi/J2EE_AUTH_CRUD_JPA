package dao;

import service.model.Student;
import service.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    List<User> studentsByFullname(String fullname);
    boolean emailExist(String email);
    User getUserById(Long id);
    User auth(User user);
    void delete(Long id);
    String MD5(String val) throws Exception;
}
