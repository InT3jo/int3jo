package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.service.users.JoinService;
import project3.yakdo.validation.form.JoinForm;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

	private final JoinService joinService;
	
	@GetMapping("/join?현재주소")
	public String join (Model model,HttpServletRequest req) {
		JoinForm joinForm = new JoinForm();
		model.addAttribute("joinForm", joinForm);

		return "/users/join/join";
	}

	@PostMapping("/join")
	public String doJoin (@ModelAttribute JoinForm joinForm) {
		Integer result = joinService.join(joinForm);
		log.info("가입시도");
		//insert 실패, 성공 여부에 따라
		//페이지 경로 잡아주기
		if(result == 1) {
			log.info("가입성공");
			return "/home";
		}
		log.info("가입실패");
		return "/users/join/join";
	}
}
