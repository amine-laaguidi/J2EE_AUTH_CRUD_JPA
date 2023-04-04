package web;

import java.util.List;

import dao.StudentDaoImpl;
import dao.StudentDao;
import service.model.Student;
public class TestDao {
    public static void main(String[] args) {
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

        //REPOSITORIES
        StudentDao studentDao = new StudentDaoImpl();

        studentDao.save(student);
    }
}