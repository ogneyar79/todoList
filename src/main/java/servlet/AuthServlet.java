package servlet;

import model.Users;
import org.jboss.logging.Logger;
import persistance.UserHibernate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private static Logger LOGAUTH = Logger.getLogger(AuthServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println(" doPost + AuthServlet");
        LOGAUTH.info("21 AuthServlet DOGET BEGIN");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Users user = UserHibernate.instOf().findByEmail(email);
        if (!user.getName().equals("Zero") && user.getPassword().equals(password)) {
            System.out.println(" doPostAuthServlet! && user.getPassword().equals(password)");
            HttpSession sc = req.getSession();
            sc.setAttribute("name", user.getName());
            sc.setAttribute("email", email);
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            System.out.println(" doPostAuthServlet else 35");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
