/**
 * 게시판 페이지 컨트롤러
 * 담당: 배고운
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.repository.BBSRepository;
@Slf4j
@Controller
@RequiredArgsConstructor//초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/BBS")	
public class BBSController {

	private final BBSRepository BBSRepository;
	
	//BBS타입 리스트 
	@GetMapping("/BBSlist")
	public String BBSList(Model model) {
		List<BBS> bbsListZero = BBSRepository.selectByShowZero();
		log.info("bbsListZero {}", bbsListZero);
		model.addAttribute("bbsListZero", bbsListZero);
		return "BBS/BBSlist";		
	}
	
	

	
	//insert
	@GetMapping("/BBSwrite")
	public String BBSwrite(Model model) {
		model.addAttribute("BBS", new BBS());
		return "BBS/BBSwrite";
	}
	
	//insert	
	@PostMapping("/BBSwrite")
	public String newBBSInsertModel
		(
			@ModelAttribute BBS bbs
//			,RedirectAttributes rAttr
			,BindingResult bindingResult
		) 
	{
		
		if (!StringUtils.hasText(bbs.getBbsTitle()))// null, 글씨없는거, 공백만있는거 다 체크 해줌
		{
			bindingResult.addError(new FieldError("bbs", "bbsTitle", bbs.getBbsTitle(), false,
					new String[] { "required.bbs.bbsTitle" }, null, "제목 필수입력."));
		}
		
		if (!StringUtils.hasText(bbs.getBbsContent()))// null, 글씨없는거, 공백만있는거 다 체크 해줌
		{
			bindingResult.addError(new FieldError("bbs", "bbsContent", bbs.getBbsContent(), false,
					new String[] { "required.bbs.bbsContent" }, null, "내용 필수입력."));
		}
		
		if (bindingResult.hasErrors()) // 오류가 없으면 비어있으니까
		{
			log.info("bindingResult={}", bindingResult);
			return "BBS/BBSlist";
		}
		
		BBSRepository.insertBBS(bbs);
		
//		rAttr.addAttribute("bbsNo",bbs.getBbsNo());
//		rAttr.addAttribute("test","ok");
		

		return "redirect:/BBS/BBSlist";

	}
	

	
	//selectBybbsNo
	@PostMapping("/BBSview")
	public String BBSview2(Model model, @RequestParam("bbsNo") int bbsNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSview";
	}
	
	//selectBybbsNo
	@GetMapping("/BBSlist/{bbsNo}")
	public String BBSview(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSview";
	}
	
	
	@GetMapping("/update/{bbsNo}")//어떤 bbsNo에 대한 업데이트를 할거냐
	public String updateBBS(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS",bbsItem);
		return "BBS/BBSupdate.html";
	}
	
	@PostMapping("/update/{bbsNo}")
	public String updateBBSprocess(Model model, @PathVariable("bbsNo") int bbsNo
			,@ModelAttribute BBS bbs) {
		BBSRepository.updateBBS(bbsNo, bbs);
		return "redirect:/BBS/BBSlist/{bbsNo}";
	}
}

























