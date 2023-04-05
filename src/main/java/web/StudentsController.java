package web;

import dao.StudentDao;
import dao.StudentDaoImpl;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.service.spi.InjectService;
import service.model.Student;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import javax.inject.Inject;

@WebServlet(name = "StudentsController", value = "/students")
public class StudentsController extends HttpServlet {


    private StudentDao studentDao;

    @Override
    public void init() throws ServletException {
        studentDao = new StudentDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("students");
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        if(request.getParameter("del")!=null){
            Long id = Long.parseLong(request.getParameter("del"));
            studentDao.delete(id);
        }else if(request.getParameter("update")!=null && request.getAttribute("student")==null){
                Long id = Long.parseLong(request.getParameter("update"));
                request.setAttribute("student",studentDao.getStudentById(id));
        }
        List<Student> students;
        if(request.getParameter("search")!=null)
            students= studentDao.studentsByUsername(request.getParameter("search"));
        else
            students = studentDao.studentsByUsername("");
        request.setAttribute("students",students);
        request.getRequestDispatcher("views/students.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        int age = Integer.parseInt(request.getParameter("age"));
        Student student= new Student();
        student.setUsername(username);
        student.setAge(age);
        if(request.getParameter("update")!=null){
            Long id = Long.parseLong(request.getParameter("update"));
            student.setId(id);
            if(!username.equals(studentDao.getStudentById(id).getUsername()) && studentDao.usernameExist(username) ){
                request.setAttribute("student", student);
                request.setAttribute("err1",true);
            }else{
                studentDao.update(student);
                response.sendRedirect(request.getRequestURI());
                return;
            }
        }else if(studentDao.usernameExist(username)){
            request.setAttribute("student", student);
            request.setAttribute("err1",true);
        } else{
            studentDao.save(student);
        }
        this.doGet(request,response);
    }
}
