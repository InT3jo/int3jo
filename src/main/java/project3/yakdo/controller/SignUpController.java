package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.service.users.JoinService;
import project3.yakdo.validation.form.JoinForm;

@Slf4j
@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class SignUpController {

	private final JoinService joinService;
	
	
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
	@PostMapping
	public String signUp(@ModelAttribute JoinForm joinForm) {
		joinService.signUp(joinForm);
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
		JoinForm joinForm = new JoinForm();
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
	public String addInfo(@ModelAttribute JoinForm joinForm) {
		joinService.addInfo(joinForm);
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
		JoinForm joinForm = new JoinForm();
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
	public String addUsingDrugs(@ModelAttribute JoinForm joinForm) {
		log.info("joinForm = {}", joinForm);
		joinService.addUsingDrugs(joinForm);
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
		JoinForm joinForm = new JoinForm();
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
	public String addAllergy(@ModelAttribute JoinForm joinForm) {
		joinService.addAllergy(joinForm);
		return "/users/join/addAllergy";
	}
	
	
	
	
	/** 회원가입 html 나누기 전 메소드
	 * 
	 * @param req
	 * @param model
	 * @return
	@PostMapping("/join")
	public String dojoin(HttpServletRequest req, Model model) {
		
		List<String> allergyList = getParamList(req, "allergy");
		JoinForm joinForm = new JoinForm();
		joinForm.setAllergy(allergyList);

		log.info("allergyList {}", allergyList);
		
		//JoinForm에 이런식으로 넣고
//		JoinForm joinForm = new JoinForm();
		//joinForm.setUserEmail(userEmail);
		
				
//		Integer result = joinService.join(joinForm);
//		log.info("가입시도");
//		
//		//insert 실패, 성공 여부에 따라
//		//페이지 경로 잡아주기
//		if(result == 4) {
//			log.info("JoinForm {}", joinForm);
//			log.info("가입 시도 email : {} 가입성공", joinForm.getUserEmail());
//			return "/home";
//		}
//		log.info("가입실패");
		return "/users/join/join";
	}

	 */

	/** 회원가입 html 나누기 전 메소드
	 * request로 넘어온 param을 List에 추가하는 메소드
	 * @param param
	 * @return paramList
	public List<String> getParamList(HttpServletRequest req, String paramKey) {
		List<String> paramList = new ArrayList<String>();
		int temp = 1;
		
		while(true) {
			String paramName = paramKey+temp;
			if(req.getParameter(paramName)==null) {	//paramName이 null 이면 저장 안함
				break;
			}
			if(req.getParameter(paramName).equals(""+temp)) {	//paramName이 공백이면 저장 안하고 다음 param 가져옴
				continue;
			}
			paramList.add(req.getParameter(paramName));
			log.info(req.getParameter(paramName));
			temp++;
		}
		return paramList;
	}
	 */
	
	
	/**
	 * 회원가입 창
	 * 
	 * @param model
	 * @return /users/join/join
	 * 
	 * 담당자 : 빙예은
	@GetMapping("/join")
	public String join (Model model) {
		JoinForm joinForm = new JoinForm();
		model.addAttribute("joinForm", joinForm);

		return "/users/join/join";
	}

	 */
	
	/**
	 * join.html에서 가입하기 눌렀을 때
	 * insert 처리에 따라 이동할 경로 지정하는 메소드
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
	@PostMapping("/join")
	public String doJoin (@ModelAttribute JoinForm joinForm) {
		Integer result = joinService.join(joinForm);
		log.info("가입시도");
		
		//insert 실패, 성공 여부에 따라
		//페이지 경로 잡아주기
		if(result == 4) {
			log.info("JoinForm {}", joinForm);
			log.info("가입 시도 email : {} 가입성공", joinForm.getUserEmail());
			return "/home";
		}
		log.info("가입실패");
		return "/users/join/join";
	}
	
	 */
}
