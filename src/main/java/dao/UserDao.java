package dao;

import service.model.Student;
import service.model.User;

import javax.ejb.Local;
import java.util.List;
@Local
public interface UserDao {
    User save(User user) throws Exception;
    List<User> usersByFullname(String fullname);
    boolean emailExist(String email);
    User getUserById(Long id);
    User auth(User user) throws Exception;
    void delete(Long id);
    String MD5(String val) throws Exception;
}
