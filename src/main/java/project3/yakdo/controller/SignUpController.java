package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.service.users.SignUpService;
import project3.yakdo.validation.form.SignUpForm;

/**
 * 회원가입이 이뤄지는 controller
 * @author honey
 *
 * 담당자 : 빙예은
 */
@Slf4j
@Controller
@RequestMapping("/signUp")
@RequiredArgsConstructor
public class SignUpController {
	
//	private final JoinService joinService;
	private final SignUpService signUpService;
	
	/**
	 * 회원가입 창
	 * @return "/users/signUp/signUp";
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping
	public String signUpForm (Model model) {
		SignUpForm signUpForm = new SignUpForm();
		model.addAttribute("signUpForm", signUpForm);

		return "/users/signUp/signUp";
	}
	
	/**
	 * 회원가입 진행
	 * @param joinForm
	 * @return 회원가입 성공하면 welcome, 아니면 그대로
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping
	public String signUp(@ModelAttribute SignUpForm signUpForm) {
		log.info("signUp joinForm = {}", signUpForm);
		
//		Integer result =
				signUpService.signUp(signUpForm);
//		if(result == 1) {
//			return "/welcome";
//		}
		return "redirect:/login";
	}
}
