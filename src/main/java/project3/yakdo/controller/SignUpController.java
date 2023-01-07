package project3.yakdo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 회원가입 창
	 * @return "/users/signUp/signUp";
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping
	public String signUpForm (Model model) {
		SignUpForm signUpform = new SignUpForm();
		model.addAttribute("signUpForm", signUpform);
		
		//약 목록 넘겨주기
		List<DrugsNameForm> drugsNameFormList = findDrugService.getDrugsNameFormList();
		model.addAttribute("drugsNameFormList", drugsNameFormList);
		return "/users/signUp/signUp";
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
		List<UsersInfo> usersInfoList = signUpService.getUsersInfoList(req);
		signUpService.signUpUsersAndUsersInfo(signUpForm, usersInfoList);
		return "redirect:/login";
	}

	
	
}
