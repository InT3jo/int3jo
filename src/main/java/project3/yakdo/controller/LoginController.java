package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.service.users.LoginService;
import project3.yakdo.session.SessionVar;
import project3.yakdo.validation.form.LoginForm;
import project3.yakdo.validation.LoginValidator;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	/**
	 * login
	 * 로그인 창 처음 접근했을 때 뜨는 부분
	 * 
	 * @param model
	 * @return "/users/login/login"
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "/users/login/login";
	}
	
	/**
	 * 로그인 기능 실행
	 * 
	 * @param model
	 * @return 마지막 경로
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/login")
	public String doLogin(Model model
					, @Validated @ModelAttribute LoginForm loginForm
					, BindingResult bindingResult
					, HttpServletRequest req
					, @RequestParam(name="redirectURL", defaultValue="/") String redirectURL) {
		//로그인 실행
		Users user = loginService.login(model, loginForm, bindingResult);
		if(user == null) {
			return "/users/login/login";
		}
		//로그인 했을 때 들어오는 회원 정보
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.LOGIN_MEMBER, user);
		
		return "redirect:"+redirectURL;
	}

	
	/**
	 * 로그아웃 실행되는 메소드
	 * 
	 * @param req
	 * @return 마지막 접근했던 창으로
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/logout")
	public String logout(HttpServletRequest req, @RequestParam(name="redirectURL", defaultValue="/") String redirectURL) {
		//false = session없을 때, 새로 만들지 않고 null값 반환
		loginService.logoutService(req);
		return "redirect:"+redirectURL;
	}
	
}
