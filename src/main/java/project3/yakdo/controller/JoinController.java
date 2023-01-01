package project3.yakdo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.service.users.JoinService;
import project3.yakdo.validation.form.JoinForm;
import project3.yakdo.validation.form.UsersInfoForm;
/**
 * 
 * 현재 회원가입 작업 중,
 * 프론트 수정 완료 되면 java로 데이터 넘겨받도록 해야함
 * @author honey
 *
 */
@Slf4j
@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

	private final JoinService joinService;
	//UsersInfoForm으로 받아온 데이터 저장
	private final UsersInfo usersInfo = new UsersInfo();
	
	
	/**
	 * 기본 회원가입 창
	 * @return "/users/join/join";
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping
	public String signUpForm (Model model) {
		JoinForm joinForm = new JoinForm();
		model.addAttribute("joinForm", joinForm);

		return "/users/join/join";
	}
	
	/**
	 * 기본 정보로 회원가입 진행
	 * @param joinForm
	 * @return 회원가입 성공하면 welcome, 아니면 그대로
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/welcome")
	public String signUp(@ModelAttribute JoinForm joinForm) {
		log.info("signUp joinForm = {}", joinForm);
		
		Integer result = joinService.signUp(joinForm);
		if(result == 1) {
			return "/welcome";
		}
		return "redirect:/join";
	}
	
	/**
	 * 회원 가입 후 
	 * 본인 및 가족 건강 정보 추가 창
	 * @param model
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/addInfo")
	public String addInfoForm(Model model) {
		UsersInfoForm usersInfoForm = new UsersInfoForm();
		model.addAttribute("usersInfoForm", usersInfoForm);
		return "/users/join/addInfo";
	}
	
	/**
	 * 본인 및 가족 건강 정보 추가 실행
	 * @param joinForm
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/addUsingDrugs")
	public String addInfo(@ModelAttribute UsersInfoForm usersInfoForm) {
		//String으로 받아온 생일 date 타입으로 변환
		Date birth = Date.valueOf(usersInfoForm.getBirth());
		
		//usersInfo에 세팅
		usersInfo.setFamilyNo(usersInfoForm.getFamilyNo());
		usersInfo.setBirth(birth);
		usersInfo.setGender(usersInfoForm.getGender());
		usersInfo.setWeight(usersInfoForm.getWeight());
		
		log.info("usersInfoForm = {}", usersInfoForm);
		log.info("usersInfo = {}", usersInfo);
		
		return "/users/join/addUsingDrugs";
	}

	/**
	 * 복용 중인 약물 추가 창
	 * @param model
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/addUsingDrugs")
	public String aaddUsingDrugsForm(Model model) {
		UsersInfoForm usersInfoForm = new UsersInfoForm();
		model.addAttribute("usersInfoForm", usersInfoForm);
		return "/users/join/addUsingDrugs";
	}
	
	/**
	 * 복용 중인 약물 추가 실행
	 * @param joinForm
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/addAllergy")
	public String addUsingDrugs(
			@RequestParam("usingDrugs") String usingDrugs
			, Model model) {
		//usingDrugs를 받기 위한 리스트 생성
		List<String> usingList = new ArrayList<>();
		usingList.add(usingDrugs);
		
		//usersInfo에 세팅
		usersInfo.setUsingDrugs(usingList);
		
		log.info("usingList = {}", usingList);
		log.info("usersInfo = {}", usersInfo);
		UsersInfoForm usersInfoForm = new UsersInfoForm();
		model.addAttribute("usersInfoForm", usersInfoForm);
		
		return "/users/join/addAllergy";
	}

	/**
	 * 알러지 추가 창
	 * @param model
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/addAllergy")
	public String addAllergyForm(Model model) {
		UsersInfoForm usersInfoForm = new UsersInfoForm();
		model.addAttribute("usersInfoForm", usersInfoForm);
		return "/users/join/addAllergy";
	}
	
	/**
	 * 알러지 약물 추가 실행
	 * @param joinForm
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/joinSuccess")
	public String addAllergy(@RequestParam("allergy") String allergy) {
		
		//usingDrugs를 받기 위한 리스트 생성
		List<String> allergyList = new ArrayList<>();
		allergyList.add(allergy);
		
		//usersInfo에 세팅
		usersInfo.setAllergy(allergyList);
		
		log.info("allergyList = {}", allergyList);
		log.info("usersInfo = {}", usersInfo);

		return "redirect:/login";
	}
}
