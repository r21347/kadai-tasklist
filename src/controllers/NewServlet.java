package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;

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

        // CSRF対策
        request.setAttribute("_token", request.getSession().getId());

        //JSPにデータを送る
        // おまじないとしてのインスタンスを生成
        //↑リクエストスコープに message が入っていなければエラーとなるため、
        //画面表示時のエラー回避のため、とりあえず “文字数0のデータ” をフォームに渡すため
        request.setAttribute("task", new Task());

        //ビューとなるJSPを指定して表示する
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
        rd.forward(request, response);



        /*      //EntityManagerのインスタンスを作成
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
        em.close();*/
    }

}
