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
	public static final String SESSION_COOKIE_NAME = "tempSessionId"; // 계속 중복해서 쓸거니까
	private Map<String, Object> sessionMap = new HashMap<String, Object>();

	public void create(Object object, HttpServletResponse resp) { // 세션 만들기
		String sessionId = UUID.randomUUID().toString();
		sessionMap.put(sessionId, object);

		Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		resp.addCookie(cookie);
	}

	public void remove(HttpServletRequest req) {

		Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
		if (cookie != null) {
			sessionMap.remove(cookie.getValue());
		}
	}

	public Object getSessionObject(HttpServletRequest req) { // 벨류값에 맞는 키를 가진 오브젝트 반환
		Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
		if (cookie != null) {
			return sessionMap.get(cookie.getValue());
		}
		return null;
	}

	public Cookie findCookie(HttpServletRequest req, String cookieName) { // 찾아서 쿠키를 리턴해주면
		if (req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}

		}
		return null;
	}
}