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
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBSComment;
import project3.yakdo.repository.BBSCommentRepository;

@Slf4j
@Controller
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/comment")
public class BBSCommentController {

	private final BBSCommentRepository bbsCommentRepositoy;

	// 해당글의 댓글 리스트 불러오기
	@GetMapping("/commentList/{bbsNo}")
	public String CommentView(Model model, @PathVariable("bbsNo") int bbsNo) {
		List<BBSComment> commentListZero = bbsCommentRepositoy.selectComBybbsNo(bbsNo);
		
		log.info("commentListZero {}", commentListZero);
		model.addAttribute("commentListZero", commentListZero);
		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	

	// 댓글 쓰기
	@PostMapping("/writecom")
	public String insertBBSCom(@ModelAttribute BBSComment bbsCom, Model model) {
		bbsCommentRepositoy.insertBBSCom(bbsCom);
		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 댓글 수정
	@GetMapping()

	// 댓글 수정
	@PostMapping("/updatecom")
	public String updateCom(Model model, @PathVariable("bbsNo") int bbsNo,@PathVariable("comNo") int comNo, @ModelAttribute BBSComment bbsComment) {
		bbsCommentRepositoy.updateCom(bbsNo,comNo, bbsComment);
		return "";
	}

	// 본인 삭제
	@GetMapping("/deletecom/{comNo}")
	public String updateComShowOneBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo) {
		
		return "";
	}
	
	//본인삭제
//	@PostMapping("/delete/{comNo}")
	
	
	

}
