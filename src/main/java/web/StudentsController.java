package web;

import dao.StudentDao;
import dao.StudentDaoImpl;
import service.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentsController", value = "/students")
public class StudentsController extends HttpServlet {

    private StudentDao studentDao;

    public void init() throws ServletException{
        studentDao=new StudentDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDao.studentsByUsername("");
        request.setAttribute("students",students);
        request.getRequestDispatcher("views/students.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        int age = Integer.parseInt(request.getParameter("age"));
        Student student= new Student(username,age);
        if(studentDao.usernameExist(username)){
            request.setAttribute("student", student);
            request.setAttribute("err1",true);
        }else{
            studentDao.save(student);
        }
        this.doGet(request,response);
    }
}
