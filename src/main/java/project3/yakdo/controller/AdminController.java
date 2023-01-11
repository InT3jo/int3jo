/**
 * 관리자 페이지 컨트롤러
 * 담당: 배고운 
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.PageMaker;
import project3.yakdo.domain.BBS.Reply;
import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.BBSCommentRepository;
import project3.yakdo.repository.BBSRepository;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.service.BBS.BBSService;
import project3.yakdo.service.users.LoginService;
import project3.yakdo.service.users.UsersService;
import project3.yakdo.validation.BBSValidator;
import project3.yakdo.validation.ReplyValidator;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final BBSService bbsService;
	private final LoginService loginService;
	private final UsersService usersService;
	
	/**
	 * 관리자 페이지 기본 화면 
	 * @param model
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping
	public String admin(Model model, HttpServletRequest req) {
		if(loginService.getLoginUser(req).getUserGrade()==2) {
			return "redirect:/error/404";
		}
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		return "admin/admin";
	}
	
	/**
	 * 본인삭제 게시물 관리 + 페이징 + 검색
	 * @param scri
	 * @param model
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/adminShowOneList")
	public String adminShowOneList(@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest req) {
		if(loginService.getLoginUser(req).getUserGrade()==2) {
			return "redirect:/error/404";
		}
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		// 본인삭제 게시글 리스트 불러오기
		model.addAttribute("bbsListOne", bbsService.getShowOneList(scri));
		// 검색 결과 총 갯수 
		int count = bbsService.countSearchShowOne(scri);
		// 페이징+검색
		model.addAttribute("pageMaker", bbsService.makePage(scri,count));

		return "admin/adminShowOneList";
	}

	/**
	 * 관리자삭제 게시물 관리 + 페이징 + 검색
	 * @param scri
	 * @param model
	 * @param req
	 * @return
	 * 담당자: 배고운 
	 */
	@GetMapping("/adminShowTwoList")
	public String adminShowTwoList(@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest req) {
		if(loginService.getLoginUser(req).getUserGrade()==2) {
			return "redirect:/error/404";
		}
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		// 관리자 삭제 게시글 리스트 불러오기
		model.addAttribute("bbsListTwo", bbsService.getShowTwoList(scri));
		// 검색 결과 총 갯수 
		int count = bbsService.countSearchShowTwo(scri);
		// 페이징+검색
		model.addAttribute("pageMaker", bbsService.makePage(scri,count));
		
		return "admin/adminShowTwoList";
	}

	
	/**
	 * 게시글 복구
	 * @param model
	 * @param bbsNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@RequestMapping("/recover/{bbsNo}")
	public String updateShowZeroBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo, HttpServletRequest req) {
		if(loginService.getLoginUser(req).getUserGrade()==2) {
			return "redirect:/error/404";
		}
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		//bbsNo에 해당하는 게시글 복구 
		bbsService.recoverBBS(bbsNo);
		return "redirect:/admin/adminShowTwoList";
	}

	
	/**
	 * 관리할 회원 리스트 + 페이징 + 검색
	 * @param scri
	 * @param model
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/searchUserList")
	public String searchUserList(@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest req) {
		if(loginService.getLoginUser(req).getUserGrade()==2) {
			return "redirect:/error/404";
		}
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		//회원목록 출력 
		model.addAttribute("searchUserList", usersService.getUsersList(scri));
		//페이징
		model.addAttribute("pageMaker", usersService.makePage(scri));
		return "admin/searchUserList";
	}

	
	/**
	 * 회원 등급,블락 관리
	 * @param model
	 * @param userNo
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/updateGrade/{userNo}") 
	public String updateGrade(Model model, @PathVariable("userNo") Integer userNo, HttpServletRequest req) {
		if(loginService.getLoginUser(req).getUserGrade()==2) {
			return "redirect:/error/404";
		}
		//회원번호에 해당하는 회원 select 
		model.addAttribute("Users", usersService.getUsersByUserNo(userNo));
		return "admin/updateGrade";
	}

	
	/**
	 * 회원 등급,블락 관리 처리
	 * @param model
	 * @param userNo
	 * @param users
	 * @return
	 * 담당자 : 배고운 
	 */
	@PostMapping("/updateGrade/{userNo}")
	public String updateGradeProcess(Model model, @PathVariable("userNo") Integer userNo, @ModelAttribute Users users, HttpServletRequest req) {
		if(loginService.getLoginUser(req).getUserGrade()==2) {
			return "redirect:/error/404";
		}
		//회원 번호에 해당하는 회원의 등급, 상태 변경 
		usersService.updateUserGrade(userNo, users);
		return "redirect:/admin/searchUserList";
	}

	
	
}
