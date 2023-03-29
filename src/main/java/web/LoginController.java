package web;

import dao.UserDao;
import dao.UserDaoImpl;
import service.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

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
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");
        User user1 = new User();
        user1.setEmail(email);
        user1.setPassword(password);
        User user = userDao.auth(user1);
        if(user==null) {
            request.setAttribute("emailError", true);
            doGet(request, response);
        } else {
            user.setPassword("");
            user.setId((long)0);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("userSession",request.getSession().getId());
            response.sendRedirect(request.getContextPath()+"/students");
        }
    }
}
