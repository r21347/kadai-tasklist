package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    //コンストラクタ（フィルタのインスタンスが生成される際に実行される）
    public EncodingFilter() {
    }

    //「（フィルタの処理が不要になったため）フィルタを破棄する」というときの処理
    public void destroy() {
    }

    //フィルタとしての実行内容を定義するメソッド
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //サーブレットが処理を実行する 前 にフィルタの処理が実行される
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    //フィルタの処理がはじめて実行されるときの処理
    public void init(FilterConfig fConfig) throws ServletException {
    }

}