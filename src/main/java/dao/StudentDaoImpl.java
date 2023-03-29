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
    public Student getStudentById(Long id) {
        Connection connection = SingletonConnection.getConnection();
        Student student = null;
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("SELECT * FROM STUDENTS WHERE ID = ?");
            ps.setString(1,   id.toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            student = new Student(rs.getLong("ID"), rs.getString("USERNAME"), rs.getInt("AGE"));
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
