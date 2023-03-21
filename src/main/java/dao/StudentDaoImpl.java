package dao;

import service.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student save(Student student) {
        List<Student> students=new ArrayList<Student>();
        Connection connection = SingletonConnection.getConnection();
        try{
            PreparedStatement ps= connection.prepareStatement
                    ("INSERT INTO STUDENTS (USERNAME,AGE) VALUES (?,?)");
            ps.setString(1,student.getUsername());
            ps.setInt(2,student.getAge());
            ps.executeUpdate();
            PreparedStatement ps2=connection.prepareStatement
                    ("SELECT MAX(ID) AS MAX_ID FROM PRODUITS");
            ResultSet rs=ps2.executeQuery();
            if(rs.next()){
                student.setId(rs.getLong("MAX_ID")); // définir le id du produit inséré
            }
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return student;
    }
    @Override
    public List<Student> studentsByUsername(String username) {
        List<Student> students=new ArrayList<Student>();
        Connection connection = SingletonConnection.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT * FROM STUDENTS WHERE USERNAME LIKE ?");
            ps.setString(1,"%"+username+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student student=new Student(rs.getLong("ID"),rs.getString("USERNAME"),rs.getInt("AGE"));
                students.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean usernameExist(String username){
        Connection connection = SingletonConnection.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT * FROM STUDENTS WHERE USERNAME LIKE ?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }
    @Override
    public Student getStudentById() {
        return null;
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public Student delete(Student student) {
        return null;
    }
}
