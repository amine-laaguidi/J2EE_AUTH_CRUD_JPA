package dao;

import service.model.Student;

import java.util.List;

public interface StudentDao {
    Student save(Student student);
    List<Student> studentsByUsername(String username);
    boolean usernameExist(String username);
    Student getStudentById(Long id);
    Student update(Student student);
    void delete(Long id);
}
