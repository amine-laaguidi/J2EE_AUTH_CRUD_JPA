package dao;

import service.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public StudentDaoImpl(){
        this.emf = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.emf.createEntityManager();
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
        Query query = entityManager.createQuery("SELECT * FROM STUDENTS WHERE USERNAME LIKE ? '%" + username + "%'");
        return query.getResultList();
    }
    @Override
    public boolean usernameExist(String username){
        Query query = entityManager.createQuery("SELECT * FROM STUDENTS WHERE USERNAME LIKE ? '" + username + "%'");
        if(query.getResultList().get(0)!=null)
            return true;
        return false;
    }
    @Override
    public Student getStudentById(Long id) {
        Connection connection = SingletonConnection.getConnection();
        Student student = null;
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT * FROM STUDENTS WHERE ID = ?");
            ps.setString(1,   id.toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            student = new Student((long)rs.getInt("ID"), rs.getString("USERNAME"), rs.getInt("AGE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void update(Student student) {
        Connection connection = SingletonConnection.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement
                    ("UPDATE STUDENTS SET USERNAME="+'"'+student.getUsername()+'"'+" , AGE="+student.getAge()+" WHERE ID="+student.getId());
            System.out.println("UPDATE STUDENTS SET USERNAME="+'"'+student.getUsername()+'"'+" , AGE="+student.getAge()+" WHERE ID="+student.getId()+"");
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        Student student = getStudentById(id);
        Connection connection = SingletonConnection.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement
                    ("DELETE FROM STUDENTS WHERE ID = ?");
            System.out.println("DELETE FROM STUDENTS WHERE ID = 1");
            ps.setString(1,id.toString());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
