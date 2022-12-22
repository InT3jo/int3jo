package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.service.users.LoginService;
import project3.yakdo.validation.form.LoginForm;
import project3.yakdo.validation.form.LoginValidator;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		
		return "/login/login";
	}
	
	/**
	 * login 여부 판단하는 메소드
	 * @param model
	 * @return
	 */
	@PostMapping("/login")
	public String doLogin(@ModelAttribute LoginForm loginForm
					, BindingResult bindingResult
					, HttpServletResponse resp) {
		
		//에러 검증
		LoginValidator loginValidator = new LoginValidator();
		loginValidator.validateLoginForm(loginForm, bindingResult);
		
		//에러가 있는 경우 다시 login 화면으로
		if(bindingResult.hasErrors()) {
			return "/login/login";
		}
		
		//위 에러 없을 시 (공백이 아닐 시) 로그인 실행
		Users user = loginService.login(loginForm);
		
		//일치하는 정보 없으면 login 화면으로
		if(user == null) {
			bindingResult.reject("loginForm", "이메일 또는 비밀번호를 다시 확인해 주세요.");
			return "/login/login";
		}
		
		log.info("로그인 성공");
		
		Cookie cookie1 = new Cookie("userEmail", user.getUserEmail());
		Cookie cookie2 = new Cookie("userNo", Integer.toString(user.getUserNo()));
		
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);
		
		return "redirect:/";
	}
	
}
