package project3.yakdo.controller;

import java.net.http.HttpRequest;
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

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.UsersInfo;
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
	 * @param SignUpForm signUpForm
	 * @return 회원가입 성공하면 welcome, 아니면 그대로
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping
	public String signUp(@ModelAttribute SignUpForm signUpForm
			, HttpServletRequest req) {
		int i= 1;
		while(true) {
			//가족명칭, 생일, 성별, 몸무게 받아오기
			String familyNick = req.getParameter("familyNick"+i);
			String birth = req.getParameter("birth"+i);
			String gender = req.getParameter("gender"+i);
			String weight = req.getParameter("weight"+i);
			
			//null이면 멈춤
			if(familyNick == null && birth == null && gender == null && weight == null) {
				break;
			}
			UsersInfo userInfo = new UsersInfo();
			userInfo.setFamilyNick(familyNick);
			userInfo.setBirth(Date.valueOf(birth));
			userInfo.setGender(gender);
			userInfo.setWeight(Double.parseDouble(weight));
			
			List<String> usingDrugList = new ArrayList<String>();
			List<String> allergyList = new ArrayList<String>();
			int j = 1;
			while(j>0) {
				//복용 중 약물, 알러지 받아오기
				String usingDrug = req.getParameter("usingDrug"+j);
				String allergy = req.getParameter("allergy"+j);
				log.info("usingDrug {}, allergy {}", usingDrug, allergy);
				if(usingDrug == null) {
					j=0;
					continue;
				}
				//복용 중 약물, 알러지 리스트 추가
				usingDrugList.add(usingDrug);
				allergyList.add(allergy);

				log.info("usingDrugList {}, allergyList {}", usingDrugList, allergyList);
				j++;
			}
			//userInfo에 usingDrugList, setAllergyList 저장
			userInfo.setUsingDrugList(usingDrugList);
			userInfo.setAllergyList(allergyList);
			
			//세팅된 familyList를 signUpForm에 추가하기
			List<UsersInfo> familyList = new ArrayList<UsersInfo>();
			familyList.add(userInfo);
			signUpForm.setFamilyList(familyList);
			i++;
		}
		log.info("signUp signUpForm = {}", signUpForm);
		
//		Integer result =
				signUpService.signUp(signUpForm);
//		if(result == 1) {
//			return "/welcome";
//		}
		return "redirect:/login";
	}
}
