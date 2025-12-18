package jp.co.sss.crud.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;

@Component
public class LoginCheckFilter extends HttpFilter{
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
//			リクエストを取得
			String requestURL = request.getRequestURI();
			
			// endsWith("")これがあることにより、()に書いてあるURLなら通してもいいですよ。という処理になる。
			// /loginは入力された後の処理なので、/や/logoutが必要となる
//			ログイン画面への遷移を削除、それ以外はフィルターを実行
			if (requestURL.endsWith("/login") || requestURL.endsWith("/") || requestURL.endsWith("/logout")) {
				System.out.println("ログイン関係→フィルター通過");
				chain.doFilter(request, response);
				return ;
			}
			
			// htmlやcssが表示されるように記述している
			// 文字の中で何番目にあるかを見るメソッドで無かったら-1を返す。というメソッド
			// -1じゃなかったらturuという処理を書く（コンテインというメソッドがありそれを使った方が分かりやすい）
//			画面(view)への干渉を削除、それ以外はフィルターを実行
			if (requestURL.indexOf("/html/") != -1 || requestURL.indexOf("/css/") != -1 ||
					requestURL.indexOf("/img/") != -1 || requestURL.indexOf("/js/") != -1) {
				System.out.println("view関係→フィルター通過");
				chain.doFilter(request, response);
				return ;
			} else {
//				ログインしているかの判断
//				セッションからempIdを取り出し、存在するかで判断
				HttpSession session = request.getSession();
				// 今回キャストするのはEmployeeBeanこれ
				// 教科書はセッションにIntegerを入れているが、今回はセッションに違う型が入っている
				EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");
				
				if (loginUser == null) {
					System.out.println("ログインしていない→フィルターでキャッチ");
					response.sendRedirect("/spring_crud/");
					return;
				} else {
					System.out.println("ログインしている→フィルター通過");
					chain.doFilter(request, response);
				}
			}

			
	}

}
