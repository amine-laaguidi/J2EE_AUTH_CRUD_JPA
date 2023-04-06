package dao;

import service.model.Student;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.Local;

@Stateless
@Local(StudentDao.class)
public class StudentDaoImpl implements StudentDao {

    private final EntityManager entityManager;

    public StudentDaoImpl(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        this.entityManager = emf.createEntityManager();
    }
    @Override
    public Student save(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }
    @Override
    public List<Student> studentsByUsername(String username) {
        Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.username LIKE  '%" + username + "%'");
        try{
            return query.getResultList();
        }catch(NoResultException e){
            System.out.println("NoResultException");
            return null;
        }
    }
    @Override
    public boolean usernameExist(String username){
        Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.username LIKE  '%" + username + "%'");
        if(query.getResultList().get(0)!=null)
            return true;
        return false;
    }
    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }
    @Override
    public Student update(Student student) {
        Student studentToUpdate = getStudentById(student.getId());
        entityManager.getTransaction().begin();
        studentToUpdate.setUsername(student.getUsername());
        studentToUpdate.setAge(student.getAge());
        entityManager.getTransaction().commit();
        return studentToUpdate;
    }

    @Override
    public void delete(Long id) {
        Student studentToDelete = getStudentById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(studentToDelete);
        entityManager.getTransaction().commit();
    }
}
