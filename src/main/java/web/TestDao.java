package web;

import java.util.List;
import java.util.Properties;

import dao.StudentDaoImpl;
import dao.StudentDao;
import service.model.Student;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestDao {

    @EJB
    static StudentDao studentDao;

    public static void main(String[] args) throws NamingException {
        /*StudentDaoImpl dao=new StudentDaoImpl();
        Student s1=dao.save(new Student("usernumber1",20));
        Student s2=dao.save(new Student("usernumber2",21));
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println("Student Search : ");
        List<Student> prods=dao.studentsByUsername("%%");
        for(Student p :prods){
            System.out.println(p.toString());
        }*/
        Student student = new Student();
        student.setUsername("hello");
        student.setAge(12);
        //TODO JNDI lookup for the EJB StudentService the name is name of Bean "studentService" suffixed with "Remote" ;
        studentDao.save(student);
    }
}