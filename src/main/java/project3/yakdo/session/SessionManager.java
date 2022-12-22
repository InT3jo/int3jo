package project3.yakdo.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionManager {
	
	public static final String SESSION_COOKIE_NAME = "tempSessionEmail";
	private Map<String, Object> sessionMap = new HashMap<String, Object>();
	
	
	/**
	 * 쿠키 생성
	 * @param object
	 * @param resp
	 */
	public void create(Object object, HttpServletResponse resp) {
		String sessionEmail = UUID.randomUUID().toString();
		sessionMap.put(sessionEmail, object);
		
		Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionEmail);
		resp.addCookie(cookie);
	}
	
	
	/**
	 * 세션 정보 삭제
	 * @param req
	 */
	public void remove(HttpServletRequest req) {
		if(req.getCookies() != null) {
			for(Cookie cookie : req.getCookies()) {
				if(cookie.getName().equals(SESSION_COOKIE_NAME)) {
					sessionMap.remove(cookie.getValue());
					
					return;
				}
			}
		}
	}
	
	/**
	 * session의 value 찾기
	 * @param req
	 * @return
	 */
	public Object getSessionObject(HttpServletRequest req) {
		if(req.getCookies() != null) {
			for(Cookie cookie : req.getCookies()) {
				if(cookie.getName().equals(SESSION_COOKIE_NAME)) {
					return sessionMap.get(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	public Cookie findCookie(HttpServletRequest req, String cookieName) {
		if(req.getCookies() != null) {
			for(Cookie cookie : req.getCookies()) {
				if(cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		return null;
	}
}