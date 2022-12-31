package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	 * 담당자 : 빙예은 2022-12-23
	 */
	@GetMapping("/login")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		
		return "/users/login/login";
	}
	
	/**
	 * 로그인 기능 실행
	 * 
	 * @param model
	 * @return 마지막 경로
	 * 
	 * 담당자 : 빙예은 2022-12-23
	 */
	@PostMapping("/login")
	public String doLogin(@ModelAttribute LoginForm loginForm
					, BindingResult bindingResult
					, HttpServletRequest req
					, @RequestParam(name="redirectURL", defaultValue="/") String redirectURL) {
		
		//에러 검증
		LoginValidator loginValidator = new LoginValidator();
		loginValidator.validateLoginForm(loginForm, bindingResult);
		
		//에러가 있는 경우 다시 login 화면으로
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
		
		//위 에러 없을 시 (공백이 아닐 시) 로그인 실행
		Users user = loginService.login(loginForm);
		log.info("user {}", user);
		
		//일치하는 정보 없으면 login 화면으로
		if(user == null) {
			bindingResult.reject("loginForm", "이메일 또는 비밀번호를 다시 확인해 주세요.");
			return "/users/login/login";
		}
		
		log.info("로그인 성공");
		
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
		//session없을 때 생성되지 않게 막아주기
		HttpSession session = req.getSession(false); // 다시 세션만들어지지 않게 막기
		
		if (session != null){// 있으면 로그인 안되게 처리
			session.invalidate(); // 서버에서 세션정보 없애기
		}

		log.info("로그아웃 성공");
		
		return "redirect:"+redirectURL;
	}
	
}
