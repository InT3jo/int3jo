/**
 * 게시판 페이지 컨트롤러
 * 담당: 배고운
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.repository.BBSRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/BBS")
public class BBSController {

	private final BBSRepository BBSRepository;
	
	
	@GetMapping
	public String BBS(Model model) {
		List<BBS> BBS = BBSRepository.selectByBBS_show_0();
		model.addAttribute("BBS", BBS);
		return "BBS/BBSlist";		
	}
	
//	@GetMapping("/{}")
//	public String BBS
	
	
	
}
