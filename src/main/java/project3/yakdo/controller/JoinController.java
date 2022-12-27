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
	
	/**
	 * 회원가입 창
	 * 
	 * @param model
	 * @return /users/join/join.html
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/join")
	public String join (Model model) {
		JoinForm joinForm = new JoinForm();
		model.addAttribute("joinForm", joinForm);

		return "/users/join/join";
	}

	
	/**
	 * join.html에서 가입하기 눌렀을 때
	 * 회원가입 기능 실행되는 메소드
	 * 	--추가 될 기능
	 * 	이메일 인증
	 * 	비밀번호 검사
	 *  공백일 때 처리할 내용 등
	 *  유효성 검사 들어갈 예정
	 *  
	 * @param joinForm
	 * @return 가입 성공하면 /home
	 * @return 가입 실패 시 다시 join
	 * 
	 * 담당자 : 빙예은
	 */
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
