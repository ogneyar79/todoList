package servlet;

import model.Task;
import persistance.WorkerHibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddTask extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddTask Servlet Strong :" + 15);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String description = req.getParameter("description");
        System.out.println(description + ": Description from index.jsp to add new Task 19 String");
        System.out.println(description + ": Description addTask");
        Task task = new Task();
        task.setDescription(description);
        WorkerHibernate.instOf().add(task);
    }
}
