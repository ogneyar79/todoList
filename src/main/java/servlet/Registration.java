package servlet;

import lombok.SneakyThrows;
import model.Users;
import org.jboss.logging.Logger;
import persistance.UserHibernate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/reg")
public class Registration extends HttpServlet {
    private static Logger LOGREG = Logger.getLogger(Registration.class.getName());

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LOGREG.info("21 doPost RegistrationServlet begin");
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Users user = UserHibernate.instOf().add(new Users(name, email, password));
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
