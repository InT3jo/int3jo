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
 * Inspection Filter - 업데이트중엔 점검중 페이지 띄움
 * 담당자 : 홍준표
 */
@Slf4j
public class InspectionFilter implements Filter {
	private static final String[] InspectionWhiteList = {"/inspection","/drugs/apiUpdate","/drugs/dbupdate","/admin","/admin/*", "/css/*", "/font/*", "/cursor/*"};
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if(!PatternMatchUtils.simpleMatch(InspectionWhiteList, uri)) {
			HttpSession session = req.getSession(false);
			if (session != null && session.getAttribute(SessionVar.INSPECTION) != null && ((String)session.getAttribute(SessionVar.INSPECTION)).equals("점검중")) {
				resp.sendRedirect("/inspection");
				return;
			}
		}
		chain.doFilter(request, response);
	}

}
