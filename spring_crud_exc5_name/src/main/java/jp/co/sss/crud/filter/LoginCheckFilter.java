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
		
			String requestURL = request.getRequestURI();
			if (requestURL.endsWith("/login") || requestURL.endsWith("/") || requestURL.endsWith("/logout")) {
				chain.doFilter(request, response);
			} else {
				if (requestURL.indexOf("/html/") != -1 || requestURL.indexOf("/css/") != -1 ||
						requestURL.indexOf("/img/") != -1 || requestURL.indexOf("/js/") != -1) {
					chain.doFilter(request, response);
				}
				HttpSession session = request.getSession();
				EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");
				
				if (loginUser == null) {
					response.sendRedirect("/spring_crud/");
					return;
				} else {
					chain.doFilter(request, response);
				}
			}

			
	}

}
