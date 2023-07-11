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

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _token = request.getParameter("_token");

        //CSRF対策のチェック
        if(_token != null && _token.equals(request.getSession().getId())) {

            //EntityManagerのインスタンスを作成
            EntityManager em = DBUtil.createEntityManager();
            //EntityManagerのgetTransaction()メソッドのbegin()メソッドでトランザクションを開始する
            em.getTransaction().begin();

            //Task(DTOクラス:データを格納する)のインスタンスを生成
            Task t = new Task();

            //contentに入力された値をセットする
            String content = request.getParameter("content");
            t.setContent(content);

            //作成・更新日時をセットする
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);
            
            //データベースに保存（persistメソッド：エンティティを永続化 = DBにレコードとして保存)
            em.persist(t);
            
            //コミット
            em.getTransaction().commit();
            
            //フラッシュメッセージをセッションスコープに保存
            request.getSession().setAttribute("flush", "登録が完了しました。");
            
            //EntityManagerを解放
            em.close();

            //indexページへリダイレクト（遷移）
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}
