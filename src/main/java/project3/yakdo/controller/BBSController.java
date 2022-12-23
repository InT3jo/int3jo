/**
 * 게시판 페이지 컨트롤러
 * 담당: 배고운
 */
package project3.yakdo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.repository.BBSRepository;

@Slf4j
@Controller
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/BBS")
public class BBSController {

	private final BBSRepository BBSRepository;

	// 게시글 목록 출력
	@GetMapping("/BBSlist")
	public String BBSList(Model model) {
		List<BBS> bbsListZero = BBSRepository.selectByShowZero();
		log.info("bbsListZero {}", bbsListZero);
		model.addAttribute("bbsListZero", bbsListZero);
		return "BBS/BBSlist";
	}

	// 글쓰기 insert
	@GetMapping("/BBSwrite")
	public String BBSwrite(Model model) {
		model.addAttribute("BBS", new BBS());
		return "BBS/BBSwrite";
	}

	// 글쓰기 insert
	@PostMapping("/BBSwrite")
	public String newBBSInsertModel(@ModelAttribute BBS bbs,Model model
//			,RedirectAttributes rAttr
//			, BindingResult bindingResult
			) {

//		공백있으면 페이지 안넘어가게
//		Map<String, String> errors = new HashMap<>();
//		
//		if (!StringUtils.hasText(bbs.getBbsContent())) {
//			errors.put("bbsContent", "내용 필수입력.");
//		}
//		
//		if (!StringUtils.hasText(bbs.getBbsTitle())) {
//			errors.put("bbsTitle", "제목 필수입력.");
//		}
//		
//		


		BBSRepository.insertBBS(bbs);

//		rAttr.addAttribute("bbsNo",bbs.getBbsNo());
//		rAttr.addAttribute("test","ok");

		return "redirect:/BBS/BBSlist";

	}

	// 글 읽기 selectBybbsNo
	@PostMapping("/BBSview")
	public String BBSview2(Model model, @RequestParam("bbsNo") int bbsNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSview";
	}

	// 글 읽기 selectBybbsNo
	@GetMapping("/BBSlist/{bbsNo}")
	public String BBSview(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSview";
	}

	// 글 수정
	@GetMapping("/update/{bbsNo}") // 어떤 bbsNo에 대한 업데이트를 할거냐
	public String updateBBS(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSupdate.html";
	}

	// 글 수정
	@PostMapping("/update/{bbsNo}")
	public String updateBBSprocess(Model model, @PathVariable("bbsNo") int bbsNo, @ModelAttribute BBS bbs) {

		BBSRepository.updateBBS(bbsNo, bbs);

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 본인삭제
	@GetMapping("/delete/{bbsNo}")
	public String updateShowOneBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo) {
		
		
		
		BBSRepository.updateShowOneBybbsNo(bbsNo);
		return "redirect:/BBS/BBSlist";

	}

	// 본인삭제
	@PostMapping("/delete/{bbsNo}")
	public String updateShowOneBybbsNoProcess(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBSRepository.updateShowOneBybbsNo(bbsNo);
		return "redirect:/BBS/BBSlist";

	}

	// 관리자 삭제 
	@GetMapping("/adminDelete/{bbsNo}")
	public String updateShowTwoBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBSRepository.updateShowTwoBybbsNo(bbsNo);
		return "redirect:/BBS/BBSlist";

	}

	// 관리자 삭제
	@PostMapping("/adminDelete/{bbsNo}")
	public String updateShowTwoBybbsNoProcess(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBSRepository.updateShowTwoBybbsNo(bbsNo);
		return "redirect:/BBS/BBSlist";

	}
	
	
	//글번호에 해당하는 댓글 불러오기 
	

}
