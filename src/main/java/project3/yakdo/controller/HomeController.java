package project3.yakdo.controller;

import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.service.users.LoginService;
import project3.yakdo.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	private final LoginService loginService;
	/**
	 * session과 cookie로 유효성 검사 후
	 * 로그인한 상태가 아니면 return home
	 * 
	 * @param model
	 * @param req
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/")
	public String home(Model model
					, HttpServletRequest req) {
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);
		
		return "/home";
	}
	
	/**
	 * 점검중(DB 업데이트) 페이지
	 * 담당자: 홍준표
	 */
	@GetMapping("/inspection")
	public String inspection() {
		return "error/inspection";
	}
	
	/**
	 * 회원가입 후 넘어오는 home 화면
	 * 회원가입 완료 시에 자동으로 로그인 될 예정
	 * 그래서 바로 loginHome으로 return 해둠
	 * @param model
	 * @return
	 */
	@PostMapping("/")
	public String home() {
		return "/loginHome";
	}
	
	
	// 임시사용
	@RequestMapping("/test")
	public String test() {
		return "users/login/findPw";
	}
}
