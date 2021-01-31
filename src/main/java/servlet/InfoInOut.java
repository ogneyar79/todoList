package servlet;


import com.google.gson.Gson;
import model.Task;
import persistance.WorkerHibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/info")
public class InfoInOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        System.out.println("IIOS + doGet String :" + 19);
        String button = req.getParameter("turn");
        System.out.println(button + ": Button");
        HttpSession sc = req.getSession();
        sc.setAttribute("button", button);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Collection<Task> resultTasks;
        List<Task> tasks = WorkerHibernate.instOf().findAll();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        System.out.println("IIOS + doPost String :" + 41);
        HttpSession sc = req.getSession();
        String button = (String) sc.getAttribute("button");
        System.out.println(button + ": Button");
        if (tasks.get(0).getId() == 0) {
            System.out.println(" Empty Item ");
            resultTasks = tasks;
        } else if
        ((button == null) || button.equals("0")) {
            System.out.println(" Button 0 or button null");
            resultTasks = tasks.stream().filter(task -> task.isDone() == true).collect(Collectors.toList());
        } else {
            resultTasks = tasks;
        }
        String json = new Gson().toJson(resultTasks);
        PrintWriter out = new PrintWriter(
                resp.getOutputStream(),
                true, StandardCharsets.UTF_8);
        out.println(json);
    }
}
