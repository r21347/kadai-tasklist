package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //EntityManagerのインスタンスを作成
        EntityManager em = DBUtil.createEntityManager();
        //EntityManagerのgetTransaction()メソッドのbegin()メソッドでトランザクションを開始する
        em.getTransaction().begin();

        //Task(DTOクラス:データを格納する)のインスタンスを生成
        Task t = new Task();

        //tの各フィールドにデータを代入
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());     // 現在の日時を取得
        t.setCreated_at(currentTime);
        t.setUpdated_at(currentTime);

        String content = "タスク1";
        t.setContent(content);

        //データベースに保存（persistメソッド：エンティティを永続化 = DBにレコードとして保存)
        em.persist(t);
        //コミット
        em.getTransaction().commit();

        //自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(t.getId()).toString());

        //EntityManagerを解放
        em.close();
    }

}
