package project3.yakdo.controller;

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
import project3.yakdo.domain.users.Users;
import project3.yakdo.service.users.LoginService;
import project3.yakdo.service.users.UsersService;

@Slf4j
@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class MyPageController {
	
	private final UsersService usersService;
	private final LoginService loginService;
	
	
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
	@GetMapping("/modifyMyInfo")
	public String viewMyInfo(Model model, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);
		return "/users/myPage/modifyMyInfo";
	}

	/**
	 * 닉네임 수정 완료 창
	 * 닉네임 수정 성공 시 myPage
	 * 			실패 시 현재 페이지
	 * @param userNick
	 * @return 
	 */
	@PostMapping("/modifyMyInfo")
	public String checkModifyNick(@RequestParam("userNick") String userNick, HttpServletRequest req, Model model) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);
		
		// update가 정상적으로 실행 됐을 때 myPage로 이동
		if(usersService.compareUserNick(userNick, user.getUserNo()) == 1) {
			user.setUserNick(userNick);
			return "/users/myPage/myPage";
		}
		return "redirect:/help/modifyMyInfo";
	}
}
