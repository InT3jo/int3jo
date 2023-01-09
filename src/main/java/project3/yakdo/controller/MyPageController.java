package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.DrugsNameForm;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.service.drugs.search.FindDrugService;
import project3.yakdo.service.users.LoginService;
import project3.yakdo.service.users.UsersService;
import project3.yakdo.validation.form.PasswordForm;

@Slf4j
@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class MyPageController {

	private final UsersService usersService;
	private final LoginService loginService;
	private final FindDrugService findDrugService;

	/**
	 * 회원 탈퇴 진행을 위한 비밀번호 재확인 페이지
	 * 
	 * @param model
	 * @param req
	 * 
	 *              담당자 : 빙예은
	 */
	@GetMapping("/withdraw")
	public String confirmPw(Model model, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);
		return "/users/myPage/confirmPw";
	}

	/**
	 * 회원 탈퇴 후 완료 창 어떻게,,, 자동으로,, 로그아웃이 되는가,,,??
	 * 
	 * @param model
	 * @param req
	 * 
	 *              담당자 : 빙예은
	 */
	@PostMapping("/withdraw")
	public String leaveUser(Model model, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		// 회원 탈퇴 실행
		usersService.leaveUser(user.getUserNo());

		return "/users/myPage/leaveUser";
	}

	/**
	 * myPage 페이지
	 * 
	 * @param model
	 * @param req
	 * 
	 *              담당자 : 빙예은
	 */
	@GetMapping("/myPage")
	public String myPage(Model model, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);
		return "/users/myPage/myPage";
	}

	/**
	 * 닉네임 수정 창
	 * 
	 * @param model
	 * @param req
	 * 
	 *              담당자 : 빙예은
	 */
	@GetMapping("/modifyNickName")
	public String viewMyInfo(Model model, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);
		return "/users/myPage/modifyNickName";
	}

	/**
	 * 닉네임 수정 완료 창 닉네임 수정 성공 시 myPage 실패 시 현재 페이지
	 * 
	 * @param userNick
	 * @return
	 */
	@PostMapping("/modifyNickName")
	public String checkModifyNick(HttpServletRequest req, Model model, @RequestParam("userNick") String userNick) {
		// 업데이트 실행(실패하면 기존 정보를 가진 user 담김)
		Users user = usersService.checkModifyNick(userNick, loginService.getLoginUser(req));
		model.addAttribute("user", user);
		return "redirect:/help/myPage";
	}

	/**
	 * 비밀번호 변경 창
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@GetMapping("/modifyPassword")
	public String modifyPassword(HttpServletRequest req, Model model) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		// 비밀번호 변경 정보 담을 객체 담기
		model.addAttribute("passwordForm", new PasswordForm());
		return "/users/myPage/modifyPassword";
	}

	/**
	 * 비밀번호 변경 실행 창
	 * 
	 * @param req
	 * @param model
	 * @param PasswordForm passwordForm
	 * @return
	 */
	@PostMapping("/modifyPassword")
	public String checkModifyPw(HttpServletRequest req, Model model, @ModelAttribute PasswordForm passwordForm) {
		// 비밀번호 업데이트 실행(실패하면 기존 정보를 가진 user 담김)
		Users user = usersService.checkModifyPw(passwordForm, loginService.getLoginUser(req));
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", user);
		return "redirect:/help/modifyPassword";
	}

	/**
	 * 건강정보 조회 및 변경 페이지
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@GetMapping("/modifyMyInfo")
	public String modifyMyInfo(HttpServletRequest req, Model model) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		List<UsersInfo> usersInfoList = loginService.getUsersInfoListByUserNo(user.getUserNo());
		model.addAttribute("usersInfoList", usersInfoList);
		return "/users/myPage/modifyMyInfo";
	}

	/**
	 * 건강정보 추가하기 페이지
	 * @param req
	 * @param model
	 * @return
	 */
	@GetMapping("/addFamilyInfo")
	public String modifyMyInfoAdd(HttpServletRequest req, Model model) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		//약 목록 넘겨주기
		List<DrugsNameForm> drugsNameFormList = findDrugService.getDrugsNameFormList();
		model.addAttribute("drugsNameFormList", drugsNameFormList);
		return "/users/myPage/modifyMyInfoAdd";
	}
	
	/**
	 * 건강정보 추가하기 동작
	 * @param req
	 * @param model
	 * @return
	 */
	@PostMapping("/addFamilyInfo")
	public String addUsersInfo(HttpServletRequest req, Model model) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		usersService.addUsersInfo(user, req);
		return "redirect:/help/modifyMyInfo";
	}

	/**
	 * 건강정보 수정
	 * @param req
	 * @param model
	 * @param familyNo
	 * @return
	 */
	@GetMapping("/modifyMyInfo/{familyNo}")
	public String FamilyInfo(HttpServletRequest req, Model model, @PathVariable("familyNo") Integer familyNo) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);
		
		UsersInfo userInfo = new UsersInfo();
		List<UsersInfo> usersInfoList = loginService.getUsersInfoListByUserNo(user.getUserNo());
		for(UsersInfo usersInfo : usersInfoList) {
			if(usersInfo.getFamilyNo() == familyNo) {
				userInfo = usersInfo;
			}
		}
		if(userInfo == null) {
			return "redirect:/help/modifyMyInfo";
		}
		model.addAttribute("userInfo", userInfo);
		//약 목록 넘겨주기
		List<DrugsNameForm> drugsNameFormList = findDrugService.getDrugsNameFormList();
		model.addAttribute("drugsNameFormList", drugsNameFormList);
		return "/users/myPage/modifyFamilyInfo";
	}
	
	/**
	 * 건강정보 수정 동작
	 * @param req
	 * @param model
	 * @param familyNo
	 * @return
	 */
	@PostMapping("/modifyMyInfo/{familyNo}")
	public String modifyFamilyInfo(HttpServletRequest req, Model model, @PathVariable("familyNo") Integer familyNo) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		usersService.updateUsersInfo(user, req, familyNo);
		return "redirect:/help/modifyMyInfo";
	}
	
	/**
	 * 건강정보 삭제 동작
	 * @param req
	 * @param model
	 * @param familyNo
	 * @return
	 */
	@PostMapping("/deleteMyInfo/{familyNo}")
	public String deleteFamilyInfo(HttpServletRequest req, Model model, @PathVariable("familyNo") Integer familyNo) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		usersService.deleteUsersInfo(user, req, familyNo);
		return "redirect:/help/modifyMyInfo";
	}
}
