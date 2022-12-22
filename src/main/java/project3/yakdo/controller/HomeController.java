package project3.yakdo.controller;

import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.session.SessionManager;
import project3.yakdo.session.SessionVar;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final UsersRepository usersRepository;
	private final SessionManager sessionManager;
	
	@RequestMapping("/")
	public String home(Model model
					, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		//쿠키를 통해 넘어온 userEmail이 없는 경우
		if(session == null) {
			return "/home";
		}
		
		Users user = (Users)session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		model.addAttribute("user", user);
		

		/**
		 * session 정보 출력해보기
		 */
		Enumeration<String> sessionNames = session.getAttributeNames();
		while(sessionNames.hasMoreElements()) {
			String name = sessionNames.nextElement();
			
			log.info("session {}, {}", name, session.getAttribute(name));
		}
		
		return "/loginHome";
	}
}
