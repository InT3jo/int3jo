/**
 * 게시판 페이지 컨트롤러
 * 담당: 배고운
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.BBSInfo;
import project3.yakdo.repository.BBSRepository;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/BBS")
public class BBSController {

	private final BBSRepository BBSRepository;
	
//	//BBS타입 리스트 
//	@GetMapping("/BBSlist")
//	public String BBSList(Model model) {
//		List<BBS> BBSList = BBSRepository.selectByBBS_show_0();
////		log.info(BBSList.toString());
//		
////		log.info("비비에스넘버"+BBSList.get(0).getBBS_no());
////		log.info("BBS_title {}" , BBSList.get(0).getBBS_title());
////		BBSList에 아무것도 없음
//		
//		model.addAttribute("BBSList", BBSList);
//		
//		return "BBS/BBSlist";		
//	}
	
	//BBSInfo타입 리스트
	@GetMapping("/BBSlist")
	public String BBSInfoList(Model model) {
		List<BBSInfo> BBSInfoList = BBSRepository.selectBBSInfoByBBS_show_0();
//		log.info(BBSList.toString());
		
//		log.info("비비에스넘버"+BBSList.get(0).getBBS_no());
//		log.info("BBS_title {}" , BBSList.get(0).getBBS_title());
//		BBSList에 아무것도 없음
		
		model.addAttribute("BBSInfoList", BBSInfoList);
		
		return "BBS/BBSlist";		
	}
	
//	@GetMapping("/BBSwrite")
//	public String BBSwrite(Model model) {
//		model.addAttribute("BBS", new BBS());
//		return "BBS/BBSwrite";
//	}
	
//	생성자 때문에 안된거
//	@GetMapping("/BBSwrite")
//	public String BBSwrite(Model model) {
//		model.addAttribute("BBSItem", new BBS());
//		return "BBS/BBSwrite";
//	}
	
	@GetMapping("/BBSwrite")
	public String newBBS() {
		return "BBS/BBSwrite";
	}
	
	@PostMapping("BBSwrite")
	public String newBBSInsertModel(@ModelAttribute BBS BBS,RedirectAttributes rAttr) {
		BBSRepository.insert(BBS);
		
		rAttr.addAttribute("BBS_no",BBS.getBBS_no());
		
		return "redirect:/BBS/BBSview/{BBS_no}";
	}
	
	
	
	@GetMapping("/BBSview/{BBSNo}")
	public String BBSview() {
	
		return "BBS/BBSview";
	}
	
}
