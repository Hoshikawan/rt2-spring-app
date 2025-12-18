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
public class AccountCheckFilter extends HttpFilter{
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		// 現在アクセスされているURLを取得
		String requestURL = request.getRequestURI();
			
			//権限を持っている者への干渉を削除、それ以外はフィルターを実行
			//管理者権限が関係するURLかどうかを判定　これら以外のURLの場合は、フィルターの最後でそのまま通過させる
			if (requestURL.endsWith("/regist/input") || requestURL.endsWith("/update/input") ||
					requestURL.endsWith("/delete/check")){
				System.out.println("社員登録・更新・削除が押された");
				
				//権限があるかの判断
				//セッションからログインユーザー情報を取得
				HttpSession session = request.getSession();
				EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");	
				
				// 更新処理の場合のみ使う社員IDを取得
				// empIdはhtmlに要る。受け取る時にString型なのでいったんString型で受け取る
				// 現在の型がStringなので、後で数値に変換する
				String empId = request.getParameter("empId");
				
				// 一般ユーザは自分の社員情報のみを変更できる。
				// URLが更新画面 && ログインしているのが一般ユーザー && ログインユーザーのIDと、更新対象のIDが一致
				if (requestURL.contains("/update/input") && loginUser.getAuthority() == 1 &&
						loginUser.getEmpId() == Integer.parseInt(empId)){
					// 自分の情報なので通過
					chain.doFilter(request, response);
					return;
				}
				
				Integer authority = loginUser.getAuthority();
				
				// 管理者の場合は全て許可
				if (authority == 2) {
					System.out.println("権限あるため通過");
					chain.doFilter(request, response);
					return;	
				} else {
					// 管理者でもないし、自分の情報でもないのでログアウトさせる
					System.out.println("権限がないのでログイン画面へ");
					response.sendRedirect("/spring_crud/logout");
				}
				// 権限チェックを終了させるためのreturn
				return;
			}
			// それ以外のURLは全ユーザー通過
			chain.doFilter(request, response);
		}
	
}

