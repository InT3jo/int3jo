/**
 * 관리자 페이지 컨트롤러
 * 담당: 배고운 
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.PageMaker;
import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.BBSCommentRepository;
import project3.yakdo.repository.BBSRepository;
import project3.yakdo.repository.UsersRepository;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final BBSRepository BBSRepository;
	private final BBSCommentRepository bbsCommentRepositoy;
	private final UsersRepository usersRepository;

	@GetMapping
	public String admin() {
		return "admin/admin";
	}

	@GetMapping("/adminBBSlist")
	public String adminBBSlist(Model model) {
		// 본인삭제 게시글 리스트 불러오기
		List<BBS> bbsListOne = BBSRepository.selectByShowOne();
		model.addAttribute("bbsListOne", bbsListOne);

		// 관리자 삭제 게시글 리스트 불러오기
		List<BBS> bbsListTwo = BBSRepository.selectByShowTwo();
		model.addAttribute("bbsListTwo", bbsListTwo);

		return "admin/adminBBSlist";
	}
	
	//게시물 관리 + 페이징 + 검색 - 한페이지에 페이징 검색 둘 다 불러오는 것 보다 나누는걸로 아래에서 다시 시도 .. 
	@GetMapping("/adminBBSlistPage")
	public String adminBBSlistPage(@ModelAttribute ("scri") SearchCriteria scri,Model model) {
		// 본인삭제 게시글 리스트 불러오기
//		List<BBS> bbsListOne = BBSRepository.selectByShowOne();
//		model.addAttribute("bbsListOne", bbsListOne);
//
//		// 관리자 삭제 게시글 리스트 불러오기
//		List<BBS> bbsListTwo = BBSRepository.selectByShowTwo();
//		model.addAttribute("bbsListTwo", bbsListTwo);
		
		// 본인삭제 게시글 리스트 불러오기
		List<BBS> bbsListOne = BBSRepository.adminShowOnelist(scri);
		model.addAttribute("bbsListOne", bbsListOne);

		// 관리자 삭제 게시글 리스트 불러오기
		List<BBS> bbsListTwo = BBSRepository.adminShowTwolist(scri);
		model.addAttribute("bbsListTwo", bbsListTwo);
		
				
		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setCri(scri);
//		 pageMaker.setTotalCount(BBSRepository.listCount());
		 pageMaker.setTotalCount(BBSRepository.countSearchShowOne(scri));
		 model.addAttribute("pageMaker", pageMaker);
		
		

		return "admin/adminBBSlistPage";
	}
	
	
	//본인삭제 게시물 관리 + 페이징 + 검색  
	@GetMapping("/adminShowOneList")
	public String adminShowOneList(@ModelAttribute ("scri") SearchCriteria scri,Model model) {

		// 본인삭제 게시글 리스트 불러오기
		List<BBS> bbsListOne = BBSRepository.adminShowOnelist(scri);
		model.addAttribute("bbsListOne", bbsListOne);

		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setCri(scri);
		 pageMaker.setTotalCount(BBSRepository.countSearchShowOne(scri));
		 model.addAttribute("pageMaker", pageMaker);
		

		return "admin/adminShowOneList";
	}
	
	
	//관리자삭제 게시물 관리 + 페이징 + 검색 
	@GetMapping("/adminShowTwoList")
	public String adminShowTwoList(@ModelAttribute ("scri") SearchCriteria scri,Model model) {

		// 관리자 삭제 게시글 리스트 불러오기
		List<BBS> bbsListTwo = BBSRepository.adminShowTwolist(scri);
		model.addAttribute("bbsListTwo", bbsListTwo);
		
		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setCri(scri);
		 pageMaker.setTotalCount(BBSRepository.countSearchShowTwo(scri));
		 model.addAttribute("pageMaker", pageMaker);
		
		return "admin/adminShowTwoList";
	}
	
	
	

	// 게시글 복구
	@RequestMapping("/recover/{bbsNo}")
	public String updateShowZeroBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo) {
		BBSRepository.updateShowZeroBybbsNo(bbsNo);
		return "redirect:/admin/adminBBSlist";
	}

	// 게시글 복구
//	@GetMapping("/recover/{bbsNo}")
//	public String updateShowZeroBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo) {
//		BBSRepository.updateShowZeroBybbsNo(bbsNo);
//		return "redirect:/admin/adminBBSlist";
//	}
//	
//	@PostMapping("/recover/{bbsNo}")
//	public String updateShowZeroBybbsNoProcess(Model model, @PathVariable("bbsNo") int bbsNo) {
//		BBSRepository.updateShowZeroBybbsNo(bbsNo);
//		return "redirect:/admin/adminBBSlist";
//	}

	// 관리할 회원 리스트 불러오기 - 원래 있던 userlist 페이지로 보내주는것 주석 0104 00:47
	@GetMapping("/userlist")
	public String userlist(Model model) {
		List<Users> userList = usersRepository.selectAllUsers();
		model.addAttribute("userList", userList);
		return "admin/userlist";
	}
	

	// 관리할 회원 리스트 + 페이징 + 검색 01-04 10:24 userlist.html수정하다가 search~~ 새로 만들어서 나눔 
	
	@GetMapping("/searchUserList")
	public String searchUserList(@ModelAttribute("scri") SearchCriteria scri,Model model) {
		
//		List<Users> userList = usersRepository.selectAllUsers();
//		model.addAttribute("userList", userList);
		
		
		List<Users> searchUserList = usersRepository.searchUserList(scri);  
		model.addAttribute("searchUserList", searchUserList);
		
		PageMaker pageMaker = new PageMaker();
		
		 pageMaker.setCri(scri);
//		 pageMaker.setTotalCount(BBSRepository.listCount());
		 
		 pageMaker.setTotalCount(usersRepository.countSearchUsers(scri));
		 model.addAttribute("pageMaker", pageMaker);
		return "admin/searchUserList";
	}

	// 답변등록
//	@GetMapping("/writeAnswer/{bbsNo}")

	// 회원 등급 관리
	@GetMapping("/updateGrade/{userNo}")	//어떤 userNo에 대해서 처리할거냐
	public String updateGrade(Model model, @PathVariable("userNo") int userNo) {
		Users user = usersRepository.selectByUserNoInUsersT(userNo);
		model.addAttribute("Users", user);
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
		Users user = usersRepository.selectByUserNoInUsersT(userNo);
		model.addAttribute("Users", user);
		return "admin/updateUserStatus";
	}
	
	// 회원 블락 처리
	@PostMapping("/updateUserStatus/{userNo}")
	public String updateUserStatus(Model model, @PathVariable("userNo") int userNo, @ModelAttribute Users users) {
		usersRepository.updateUserGrade(userNo, users);
		return "redirect:/admin/userlist";
	}
	// 회원 블락 복구

}
