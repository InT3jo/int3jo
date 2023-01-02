package project3.yakdo.filter;

import java.io.IOException;

import org.springframework.util.PatternMatchUtils;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.session.SessionVar;

/**
 * 로그인 관련 필터
 * 
 * @author honey
 * 
 * 담당자 : 빙예은
 */
@Slf4j
public class LoginFilter implements Filter{

	//로그인 하지 않아도 접속 가능한 페이지 리스트
	private static final String[] whiteList = {"/", "/login", "/logout", "/signUp", "/drugs", "/drugs/*","/css/*","/font/*"};
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		HttpServletResponse resp = (HttpServletResponse) response;
		/**
		 * 로그인 안 된 경우 => 로그인 페이지로
		 * 
		 */
		if(!PatternMatchUtils.simpleMatch(whiteList, uri)) {
			HttpSession session = req.getSession(false);

			if(session == null || session.getAttribute(SessionVar.LOGIN_MEMBER) == null) {
				log.info("로그인 없이 접근 시도 {}", uri);
				resp.sendRedirect("/login?redirectURL="+uri);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	

}
