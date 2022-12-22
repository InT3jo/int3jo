/**
 * 관리자 페이지 컨트롤러
 * 담당: 배고운 
 */
package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project3.yakdo.repository.BBSRepository;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final BBSRepository BBSRepository;
	
	@GetMapping
	public String admin() {
		return "admin/admin";
	}
	
	
	
	@GetMapping("/adminBBSlist")
	public String adminBBSlist() {
		return "admin/adminBBSlist";
	}
	
	
	
	@GetMapping("/userlist")
	public String userlist() {
		return "admin/userlist";
	}
	
	
	
}
	
