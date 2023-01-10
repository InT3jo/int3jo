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
import project3.yakdo.service.users.LoginService;
import project3.yakdo.validation.BBSValidator;
import project3.yakdo.validation.ReplyValidator;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final BBSRepository BBSRepository;
	private final BBSCommentRepository bbsCommentRepositoy;
	private final UsersRepository usersRepository;
	private final LoginService loginService;

	@GetMapping
	public String admin(Model model, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		return "admin/admin";
	}

	

	// 본인삭제 게시물 관리 + 페이징 + 검색
	@GetMapping("/adminShowOneList")
	public String adminShowOneList(@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		
		// 본인삭제 게시글 리스트 불러오기
		model.addAttribute("bbsListOne", BBSRepository.adminShowOnelist(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(BBSRepository.countSearchShowOne(scri));
		model.addAttribute("pageMaker", pageMaker);

		return "admin/adminShowOneList";
	}

	// 관리자삭제 게시물 관리 + 페이징 + 검색
	@GetMapping("/adminShowTwoList")
	public String adminShowTwoList(@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		
		// 관리자 삭제 게시글 리스트 불러오기
		model.addAttribute("bbsListTwo", BBSRepository.adminShowTwolist(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(BBSRepository.countSearchShowTwo(scri));
		model.addAttribute("pageMaker", pageMaker);

		return "admin/adminShowTwoList";
	}

	// 게시글 복구
	@RequestMapping("/recover/{bbsNo}")
	public String updateShowZeroBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		BBSRepository.updateShowZeroBybbsNo(bbsNo);
		return "redirect:/admin/adminShowTwoList";
	}



	// 관리할 회원 리스트 + 페이징 + 검색 01-04 10:24 userlist.html수정하다가 search~~ 새로 만들어서 나눔

	@GetMapping("/searchUserList")
	public String searchUserList(@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		
		model.addAttribute("searchUserList", usersRepository.searchUserList(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(usersRepository.countSearchUsers(scri));
		model.addAttribute("pageMaker", pageMaker);
		return "admin/searchUserList";
	}

	// 회원 등급 관리
	@GetMapping("/updateGrade/{userNo}") // 어떤 userNo에 대해서 처리할거냐
	public String updateGrade(Model model, @PathVariable("userNo") int userNo) {
		model.addAttribute("Users", usersRepository.selectByUserNoInUsersT(userNo));
		return "admin/updateGrade";
	}

	// 회원 등급 관리 처리
	@PostMapping("/updateGrade/{userNo}")
	public String updateGradeProcess(Model model, @PathVariable("userNo") int userNo, @ModelAttribute Users users) {
		usersRepository.updateUserGrade(userNo, users);
		return "redirect:/admin/searchUserList";
	}

	// 회원 블락
	@GetMapping("/updateUserStatus/{userNo}")
	public String updateUserStatus(Model model, @PathVariable("userNo") int userNo) {
		model.addAttribute("Users", usersRepository.selectByUserNoInUsersT(userNo));
		return "admin/updateUserStatus";
	}

	// 회원 블락 처리
	@PostMapping("/updateUserStatus/{userNo}")
	public String updateUserStatus(Model model, @PathVariable("userNo") int userNo, @ModelAttribute Users users) {
		usersRepository.updateUserGrade(userNo, users);
		return "redirect:/admin/userlist";
	}
	



//	//게시판 관리자 답변 쓰기 
//	@GetMapping("/writeAnswer/{bbsNo}")
//	public String BBSwrite(Model model,@PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
//		// 현재 주소정보
//		model.addAttribute("uriHere", req.getRequestURI());
//
//		// 로그인된 유저정보(로그인되어있지 않다면 null)
//		model.addAttribute("user", loginService.getLoginUser(req));
//		
//		model.addAttribute("BBS", BBSRepository.selectBybbsNo(bbsNo));
//		
//		model.addAttribute("Reply", new Reply());
////		model.addAttribute("BBS", new BBS());
//		return "BBS/writeAnswer";
//	}
//	
//	//게시판 관리자 답변 쓰기 
//	@PostMapping("/writeAnswer/{bbsNo}")
//	public String newBBSInsertModel(@ModelAttribute Reply reply,BindingResult bindingResult,@ModelAttribute BBS bbs, @PathVariable("bbsNo") int bbsNo,Model model, HttpServletRequest req) {
//		// 현재 주소정보
//		model.addAttribute("uriHere", req.getRequestURI());
//
//		// 로그인된 유저정보(로그인되어있지 않다면 null)
//		model.addAttribute("user", loginService.getLoginUser(req));
//		
//		//내용 입력안하면 다시 글쓰는 페이지로 돌아감 
//				ReplyValidator bbsValidator = new ReplyValidator();
//				bbsValidator.validate(reply, bindingResult);
//
//				if (bindingResult.hasErrors()) {
////					return "redirect:/BBS/listSearch";
////					return "admin/writeAnswer/{bbsNo}";
//					return "BBS/writeAnswer";
//				}
//		
//		
//		BBSRepository.insertReply(reply);
//
//		return "redirect:/BBS/listSearch";
//
//	}
}
