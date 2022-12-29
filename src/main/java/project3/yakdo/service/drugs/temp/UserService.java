package project3.yakdo.service.drugs.temp;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import project3.yakdo.domain.users.Users;
import project3.yakdo.session.SessionVar;

@Service
public class UserService {
	public Users getLoginUser(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			return null;
		}
		Users user = (Users)session.getAttribute(SessionVar.LOGIN_MEMBER);
		return user;
	}
}
