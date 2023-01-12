package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.service.users.LoginService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	private final LoginService loginService;
	/**
	 * 홈 화면을 띄우는 메소드
	 * 
	 * @param model
	 * @param req
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/")
	public String home(Model model, HttpServletRequest req) {
		model.addAttribute("user", loginService.getLoginUser(req));
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

}
