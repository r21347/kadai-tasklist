package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;


@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //EntityManagerのインスタンスを作成
        EntityManager em = DBUtil.createEntityManager();

        //該当のIDのメッセージ1件のみをデータベースから取得
        Task t = em.find(Task.class, Integer.parseInt(request.getParameter("id")));

        //EntityManagerを解放
        em.close();

        //内容のデータをリクエストスコープにセットしてshow.jspを呼び出す
        request.setAttribute("task", t);

        //ビューとなるJSPを指定して表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/show.jsp");
        rd.forward(request, response);

    }

}
