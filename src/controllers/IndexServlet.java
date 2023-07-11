package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //EntityManagerのインスタンスを作成
        EntityManager em = DBUtil.createEntityManager();

        //EntityManagerのメソッドcreateNamedQueryを使用して名前付きクエリを実行する
        //getResultList()を使用してListで複数の結果を取得する
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class).getResultList();

        //EntityManagerを解放
        em.close();

        //JSPにDBから取得したデータを送る
        request.setAttribute("tasks", tasks);

        //ビューとなるJSPを指定して表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);
    }

}
