package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.DrugsNameForm;
import project3.yakdo.domain.users.SignUpForm;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.service.drugs.search.FindDrugService;
import project3.yakdo.service.users.EmailService;
import project3.yakdo.service.users.SignUpService;

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
	
	private final SignUpService signUpService;
	private final FindDrugService findDrugService;
	private final EmailService emailService;
	
	/**
	 * 회원가입 창
	 * @return "/users/signUp/signUp";
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping
	public String signUpForm (Model model, HttpServletRequest req) {
		String userEmail = req.getParameter("userEmail");
		String code = req.getParameter("code");
		String emailCodeCheck = req.getParameter("emailCodeCheck");
		if(code.equals(String.valueOf((((Integer.parseInt(emailCodeCheck)*2)+2)*2)+2))) {
			SignUpForm signUpForm = new SignUpForm();
			signUpForm.setUserEmail(userEmail);
			model.addAttribute("signUpForm", signUpForm);
			model.addAttribute("drugsNameFormList", findDrugService.getDrugsNameFormList());
			return "/users/signUp/signUp";
		}else {
			model.addAttribute("error","인증번호가 다릅니다.");
			return "redirect:/signUp/emailConfirm";			
		}
	}
	
	/**
	 * 회원가입 진행
	 * 
	 * @param SignUpForm signUpForm, HttpServletRequest req
	 * @return 회원가입 성공하면 login페이지로 이동
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping
	public String signUp(@ModelAttribute SignUpForm signUpForm
			, HttpServletRequest req) {
		signUpService.signUpUsersAndUsersInfo(signUpForm, signUpService.getUsersInfoList(req));
		return "redirect:/login";
	}

	/**
	 * 이메일 컨펌 받고, 완료시 회원가입창으로 넘김
	 * 담당자: 홍준표
	 */
	@GetMapping("/emailConfirm")
	public String emailConfirm() {
		return "/users/signUp/EmailConfirm";
	}
	
	/**
	 * 이메일 전송
	 * 담당자: 홍준표
	 */
	@PostMapping("/emailConfirm")
	public String checkEmailConfirm(Model model, HttpServletRequest req) {
		try {
			String userEmail = req.getParameter("userEmail");
			String code = emailService.sendSimpleMessage(userEmail);
			model.addAttribute("userEmail",userEmail);
			model.addAttribute("code",code);
		} catch (Exception e) {
			model.addAttribute("error","이메일 주소를 확인해주세요");
			return "/users/signUp/EmailConfirm";
		}
		return "/users/signUp/EmailConfirm";
	}
	
}
