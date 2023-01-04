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
		SignUpForm signUpform = new SignUpForm();
		model.addAttribute("signUpForm", signUpform);
		return "/users/signUp/signUp";
	}
	
	/**
	 * 회원가입 진행
	 * .getParameter으로 넘어온 값을
	 * usersInfo에 저장한 후, signUpForm에 담는다
	 * 
	 * @param SignUpForm signUpForm, HttpServletRequest req
	 * @return 회원가입 성공하면 login, 아니면 그대로
	 * 
	 * 담당자 : 빙예은
	 */
	@PostMapping
	public String signUp(@ModelAttribute SignUpForm signUpForm
			, HttpServletRequest req) {
		List<UsersInfo> tempFamilyList = new ArrayList<UsersInfo>();
		int i= 1;
		while(true) {
			//familyNick, birth, gender, weight 받아오기
			String familyNick = req.getParameter("familyNick"+i);
			String birth = req.getParameter("birth"+i);
			String gender = req.getParameter("gender"+i);
			String weight = req.getParameter("weight"+i);
			
			//파라미터가 null이면 while문 break;
			if(familyNick == null && birth == null && gender == null && weight == null) {
				break; 
			}
			
			//UsersInfo에 파라미터 세팅
			UsersInfo userInfo = new UsersInfo();
			userInfo.setFamilyNick(familyNick);
			userInfo.setBirth(Date.valueOf(birth));
			userInfo.setGender(gender);
			userInfo.setWeight(Double.parseDouble(weight));
			
			//usingDrugList 생성
			List<String> usingDrugList = new ArrayList<String>();
			//usingDrugList 만들기
			int no = 1;
			while(no>0) {
				//파라미터 가져와서 usingDrug로 초기화
				String usingDrug = req.getParameter("usingDrug"+no);
				if(usingDrug == null) {
					break;
				}
				//usingDrugList에 usingDrug 담기
				usingDrugList.add(usingDrug);
				no++;
			}
			
			//allergyList 생성
			List<String> allergyList = new ArrayList<String>();
			//allergyList 만들기
			no = 1;
			while(no>0) {
				//파라미터 가져와서 allergy로 초기화
				String allergy = req.getParameter("allergy"+no);
				if(allergy == null) {
					break;
				}
				//allergyList에 allergy 담기
				allergyList.add(allergy);
				no++;
			}
			
			//userInfo에 준비된 usingDrugList, setAllergyList setting
			userInfo.setUsingDrugList(usingDrugList);
			userInfo.setAllergyList(allergyList);
			
			//세팅된 userInfo를 signUpForm의 familyList에 추가하기
			tempFamilyList.add(userInfo);
			
			i++;
		}
		//세팅 완료된 tempfamilyList signUpForm의 familyList에 저장
		signUpForm.setFamilyList(tempFamilyList);
		log.info("signUp signUpForm = {}", signUpForm);
		
//		Integer result =
				signUpService.signUp(signUpForm);
//		if(result == 1) {
//			return "/welcome";
//		}
		return "redirect:/login";
	}
	
}
