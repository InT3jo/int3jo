package project3.yakdo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.service.users.JoinService;
import project3.yakdo.validation.form.JoinForm;
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
public class SignUpController {

	private final JoinService joinService;
	JoinForm joinForm = new JoinForm();
	List<String> usingDrugList = new ArrayList<String>();
	List<String> allergyList = new ArrayList<String>();
	
	
	/**
	 * 기본 회원가입 창
	 * @return "/users/join/join";
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping
	public String signUpForm (Model model) {
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
	@PostMapping
	public String signUp(@RequestParam("userEmail") String userEmail
					, @RequestParam("userPw") String userPw
					, @RequestParam("userNick") String userNick
					, Model model) {
		joinForm.setUserEmail(userEmail);
		joinForm.setUserPw(userPw);
		joinForm.setUserNick(userNick);
		
		log.info("signUp joinForm = {}", joinForm);
		model.addAttribute("joinForm", joinForm);
		
//		joinService.signUp(joinForm);
		return "/users/join/welcome";
	}
	
	/**
	 * 기본 정보로 회원가입 성공하면 띄워줄 환영 페이지화면
	 * @param joinForm
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/welcome")
	public String welcome () {
		return "/users/join/welcome";
	}
	
	
	/**
	 * 본인 및 가족 건강 정보 추가 창
	 * @param model
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/addInfo")
	public String addInfoForm(Model model) {
		model.addAttribute("joinForm", joinForm);
		
		return "/users/join/addInfo";
	}
	
	/**
	 * 본인 및 가족 건강 정보 추가 실행
	 * @param joinForm
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/addInfo")
	public String addInfo(@RequestParam("familyNo") String familyNo
						, @RequestParam("birth") String birth
						, @RequestParam("gender") String gender
						, HttpServletRequest req
						, Model model) {
		
		joinForm.setFamilyNo(Integer.parseInt(familyNo));
		joinForm.setBirth(birth);
		joinForm.setGender(gender);
		
		model.addAttribute("joinForm", joinForm);
		
		log.info("addInfoForm joinForm = {}", joinForm);
		
//		joinService.saveInfo(joinForm);
		return "/users/join/addInfo";
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
		model.addAttribute("joinForm", joinForm);
		return "/users/join/addUsingDrugs";
	}
	
	/**
	 * 복용 중인 약물 추가 실행
	 * @param joinForm
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/addUsingDrugs")
	public String addUsingDrugs(@RequestParam("usingDrugs") String usingDrug
							, Model model) {
		usingDrugList.add(usingDrug);
		joinForm.setUsingDrugs(usingDrugList);
		
		log.info("addUsingDrugs joinForm = {}", joinForm);
		
		model.addAttribute("joinForm", joinForm);
//		joinService.addUsingDrugs(joinForm);
		return "/users/join/addUsingDrugs";
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
		model.addAttribute("joinForm", joinForm);
		return "/users/join/addAllergy";
	}
	
	/**
	 * 알러지 약물 추가 실행
	 * @param joinForm
	 * @return
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping("/addAllergy")
	public String addAllergy(@RequestParam("allergy") String allergy
						, Model model) {
		allergyList.add(allergy);
		joinForm.setUsingDrugs(allergyList);
		
		log.info("addAllergy joinForm = {}", joinForm);
		
		model.addAttribute("joinForm", joinForm);
//		joinService.addAllergy(joinForm);
		return "/users/join/addAllergy";
	}
}
