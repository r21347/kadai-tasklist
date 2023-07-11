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

        /*       //EntityManagerのメソッドcreateNamedQueryを使用して名前付きクエリを実行する
        //getResultList()を使用してListで複数の結果を取得する
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class).getResultList();

        //EntityManagerを解放
        em.close();

        //DBから取得したデータをリクエストスコープに保存
        request.setAttribute("tasks", tasks);*/


        // 開くページ数を取得（デフォルトは1ページ目）
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        // 最大件数と開始位置を指定してメッセージを取得
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class)
                                   .setFirstResult(15 * (page - 1))
                                   .setMaxResults(15)
                                   .getResultList();

        // 全件数を取得
        long tasks_count = (long)em.createNamedQuery("getTasksCount", Long.class)
                                      .getSingleResult();

        em.close();

        request.setAttribute("tasks", tasks);
        request.setAttribute("tasks_count", tasks_count);     // 全件数
        request.setAttribute("page", page);                   // ページ数

        //フラッシュメッセージがセッションスコープにセットされていたらリクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        //ビューとなるJSPを指定して表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);
    }

}
