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
	private final LoginValidator loginValidator;
	
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
		//login 관련 유효성 검사
		loginValidator.validate(loginForm, bindingResult);
		//에러가 있는 경우 다시 login 화면으로
		if(bindingResult.hasFieldErrors("loginEmail")) {
			model.addAttribute("emailError", bindingResult.getFieldError("loginEmail").getCode());
			return "/users/login/login";
		}
		
		if(bindingResult.hasFieldErrors("loginPw"))	{
			model.addAttribute("passwordError", bindingResult.getFieldError("loginPw").getCode());
			return "/users/login/login";
		}

		//위 에러 없을 시 (공백이 아닐 시) 로그인 실행
		Users user = loginService.login(loginForm);
		loginForm.setUser(user);
		
		if(bindingResult.hasFieldErrors("user")) {
			model.addAttribute("userError", bindingResult.getFieldError("user").getCode());
			return "/users/login/login";
		}
		
////		일치하는 정보 없으면 login 화면으로
//		if(user == null) {
//			bindingResult.reject("loginForm", "이메일 또는 비밀번호를 다시 확인해 주세요.");
//			return "/users/login/login";
//		}
		
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
		HttpSession session = req.getSession(false);
		
		if (session != null && session.getAttribute(SessionVar.LOGIN_MEMBER) != null){
			session.removeAttribute(SessionVar.LOGIN_MEMBER);
		}
		return "redirect:"+redirectURL;
	}
	
}
