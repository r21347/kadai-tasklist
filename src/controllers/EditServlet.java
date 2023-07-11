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


@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //EntityManagerのインスタンスを作成
        EntityManager em = DBUtil.createEntityManager();

        //該当のIDのメッセージ1件のみをデータベースから取得
        Task t = em.find(Task.class, Integer.parseInt(request.getParameter("id")));

        //EntityManagerを解放
        em.close();

        //内容のデータをリクエストスコープに登録
        request.setAttribute("task", t);

        //セッションIDをリクエストスコープに登録
        request.setAttribute("_token", request.getSession().getId());

        //タスクIDをセッションスコープに登録
        request.getSession().setAttribute("task_id", t.getId());

      //ビューとなるJSPを指定して表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
        rd.forward(request, response);
    }

}
