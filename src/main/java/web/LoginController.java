package web;

import dao.UserDao;
import dao.UserDaoImpl;
import service.model.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    private UserDao userDao;

    public void init() throws ServletException {
        userDao = new UserDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null)
            response.sendRedirect(request.getContextPath()+"/students");
        else {
            if (request.getAttribute("user") == null)
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
        User user = null;
        try {
            user = userDao.auth(user1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(user==null) {
            request.setAttribute("emailError", true);
            doGet(request, response);
        } else {
            user.setPassword("");
            user.setId((long)0);
            request.getSession().setAttribute("user",user);
            response.sendRedirect(request.getContextPath()+"/students");
        }
    }
}
