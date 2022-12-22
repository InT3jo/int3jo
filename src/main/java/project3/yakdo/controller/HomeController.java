package project3.yakdo.controller;

import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model
					, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		//쿠키를 통해 넘어온 userEmail이 없는 경우
		if(session == null) {
			return "home";
		}
		
		/**
		 * session 정보 출력해보기
		 */
		Enumeration<String> sessionNames = session.getAttributeNames();
		while(sessionNames.hasMoreElements()) {
			String name = sessionNames.nextElement();
			
			log.info("session {}, {}", name, session.getAttribute(name));
		}

		Users user = (Users)session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		if (user == null) {
			return "home";
		}
		
		model.addAttribute("user", user);
		
		return "loginHome";
	}
}
