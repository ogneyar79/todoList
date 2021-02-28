package servlet;


import com.google.gson.Gson;
import model.Task;
import model.Users;
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
        sc.getAttribute("user");
        sc.setAttribute("button", button);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        HttpSession sc = req.getSession();
        String button = (String) sc.getAttribute("button");
        Users user = (Users) sc.getAttribute("user");
        System.out.println("IIOS + doPost USER :" + user.toString());
        Collection<Task> resultTasks;
        List<Task> tasks = WorkerHibernate.instOf().getByUser(user);
        System.out.println(button + ": Button" + "IIOS + doPost + 49");
        if (tasks.isEmpty() || tasks.get(0).getId() == 0) {
            System.out.println(" Empty Item ");
            Task empty = new Task();
            empty.setUser(user);
            empty.setDescription("EMPTY Task");
            tasks.add(empty);
            resultTasks = tasks;
        } else if
        ((button == null) || button.equals("0")) {
            System.out.println(" Button 0 or button null + 55");
            resultTasks = tasks.stream().filter(task -> task.isDone() == true).collect(Collectors.toList());
        } else {
            System.out.println("  + 58");
            resultTasks = tasks;
        }
        String json = new Gson().toJson(resultTasks);
        PrintWriter out = new PrintWriter(
                resp.getOutputStream(),
                true, StandardCharsets.UTF_8);
        out.println(json);
    }
}
