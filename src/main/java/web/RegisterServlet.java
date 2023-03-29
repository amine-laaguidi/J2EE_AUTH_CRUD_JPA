package web;

import dao.StudentDao;
import dao.StudentDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import service.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDao userDao;

    public void init() throws ServletException{
        userDao=new UserDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null || session.getAttribute("userSession")==session.getId())
            response.sendRedirect(request.getContextPath()+"/students");
        else {
            if(session.getAttribute("user")!=null){
                session.removeAttribute("user");
                session.removeAttribute("userSession");
            }
            if (request.getAttribute("user") != null)
                request.setAttribute("user", new User());
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname").trim();
        String email = request.getParameter("email").trim();
        User user = new User();
        user.setFname(fname);
        user.setEmail(email);
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if(userDao.emailExist(email)){
            request.setAttribute("emailError",true);
            request.setAttribute("user",user);
        }else if(!password2.equals(password)){
            request.setAttribute("passError",true);
            request.setAttribute("user",user);
        }else{
            try {
                user.setPassword(password);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            userDao.save(user);
            user.setPassword("");
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("userSession",request.getSession().getId());
            response.sendRedirect(request.getContextPath()+"/students");
            return;
        }
        doGet(request,response);
    }
}