package project3.yakdo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

	@GetMapping("/join")
	public String join (Model model) {
		Users users = new Users();
		UsersInfo usersInfo = new UsersInfo();
		
		model.addAttribute("users", users);
		model.addAttribute("usersInfo", usersInfo);
		return "/users/join/join";
	}
//	
//	@PostMapping("/login")
//	public String doLogin(@ModelAttribute LoginForm loginForm
//					, BindingResult bindingResult
//					, HttpServletRequest req
//					, @RequestParam(name="redirectURL", defaultValue="/") String redirectURL) {
//		
//		//에러 검증
//		LoginValidator loginValidator = new LoginValidator();
//		loginValidator.validateLoginForm(loginForm, bindingResult);
//		
//		//에러가 있는 경우 다시 login 화면으로
//		if(bindingResult.hasErrors()) {
//			return "login/login";
//		}
//		
//		//위 에러 없을 시 (공백이 아닐 시) 로그인 실행
//		Users user = loginService.login(loginForm);
//		log.info("user {}", user);
//		
//		//일치하는 정보 없으면 login 화면으로
//		if(user == null) {
//			bindingResult.reject("loginForm", "이메일 또는 비밀번호를 다시 확인해 주세요.");
//			return "/users/login/login";
//		}
//		
//		log.info("로그인 성공");
//		
//		//로그인 했을 때 들어오는 회원 정보
//		HttpSession session = req.getSession();
//		session.setAttribute(SessionVar.LOGIN_MEMBER, user);
//		
//		return "redirect:"+redirectURL;
//	}

	@PostMapping("/join")
	public String doJoin (@ModelAttribute  Map<String, Object> userInfoMap) {
		Users user = (Users) userInfoMap.get("user");
		Users userInfo = (Users) userInfoMap.get("userInfo");
		
		log.info("user {}", user);
		log.info("userInfo {}", userInfo);
//		
//		if(userInfoMap.isEmpty()) {
//			Users user = joinService.join(userInfoMap.get("user"), userInfoMap.get("userInfo"));
//		}
		return "redirect:/";
	}
}
