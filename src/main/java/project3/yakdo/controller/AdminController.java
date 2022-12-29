/**
 * 관리자 페이지 컨트롤러
 * 담당: 배고운 
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBS;
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
	//본인삭제 게시글 리스트 불러오기 
		List<BBS> bbsListOne = BBSRepository.selectByShowOne();
		model.addAttribute("bbsListOne",bbsListOne);
		
	//관리자 삭제 게시글 리스트 불러오기 
		List<BBS> bbsListTwo = BBSRepository.selectByShowTwo();
		model.addAttribute("bbsListTwo",bbsListTwo);
		
		return "admin/adminBBSlist";
	}
	
	//게시글 복구
	@RequestMapping("/recover/{bbsNo}")
	public String updateShowZeroBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo) {
		BBSRepository.updateShowZeroBybbsNo(bbsNo);
		return "redirect:/admin/adminBBSlist";
	}
	
	//게시글 복구
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
	
	
	//관리할 회원 리스트 불러오기 
	@GetMapping("/userlist")
	public String userlist(Model model) {
		List<Users> userList = usersRepository.selectAllUsers();
		model.addAttribute("userList", userList);
		return "admin/userlist";
	}
	
	
	//답변등록
//	@GetMapping("/writeAnswer/{bbsNo}")
	
	 
	
	
}
	
