package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
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
	
	
	/**
	 * 회원 탈퇴 진행을 위한 비밀번호 재확인 페이지
	 * 
	 * @param model
	 * @param req
	 * 
	 * 담당자 : 빙예은
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
	 * 회원 탈퇴 후 완료 창
	 * 어떻게,,, 자동으로,, 로그아웃이 되는가,,,??
	 * 
	 * @param model
	 * @param req
	 * 
	 * 담당자 : 빙예은
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
	 * @param model
	 * @param req
	 * 
	 * 담당자 : 빙예은
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
	 * @param model
	 * @param req
	 * 
	 * 담당자 : 빙예은
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
	 * 닉네임 수정 완료 창
	 * 닉네임 수정 성공 시 myPage
	 * 			실패 시 현재 페이지
	 * @param userNick
	 * @return 
	 */
	@PostMapping("/modifyNickName")
	public String checkModifyNick(HttpServletRequest req, Model model
								, @RequestParam("userNick") String userNick) {
		// 업데이트 실행(실패하면 기존 정보를 가진 user 담김) 
		Users user = usersService.checkModifyNick(userNick, loginService.getLoginUser(req));
		model.addAttribute("user", user);
		return "redirect:/help/myPage";
	}
	

	/**
	 * 비밀번호 변경 창
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
		
		//비밀번호 변경 정보 담을 객체 담기
		model.addAttribute("passwordForm", new PasswordForm());
		return "/users/myPage/modifyPassword";
	}
	
	/**
	 * 비밀번호 변경 실행 창
	 * @param req
	 * @param model
	 * @param PasswordForm passwordForm
	 * @return
	 */
	@PostMapping("/modifyPassword")
	public String checkModifyPw(HttpServletRequest req, Model model
								, @ModelAttribute PasswordForm passwordForm
								) {
		// 비밀번호 업데이트 실행(실패하면 기존 정보를 가진 user 담김) 
		Users user = usersService.checkModifyPw(passwordForm, loginService.getLoginUser(req));
		if(user == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", user);
		return "redirect:/help/modifyPassword";
	}

	/**
	 * 정보 변경 창
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
		return "/users/myPage/modifyMyInfo";
	}

	/**
	 * 정보 변경 실행 창
	 * @param req
	 * @param model
	 * @param PasswordForm passwordForm
	 * @return
	 */
	@PostMapping("/modifyMyInfo")
	public String checkModifyInfo(HttpServletRequest req, Model model) {
		// 비밀번호 업데이트 실행(실패하면 기존 정보를 가진 user 담김) 
		return "redirect:/help/myPage";
	}
}
