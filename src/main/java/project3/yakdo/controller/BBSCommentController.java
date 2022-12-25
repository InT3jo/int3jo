package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBSComment;
import project3.yakdo.repository.BBSCommentRepository;

@Slf4j
@Controller
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/comment")
public class BBSCommentController {

	private final BBSCommentRepository BBSCommentRepositoy;
	
	//해당글의 댓글 리스트 불러오기
	@GetMapping("/commentList")
	public String CommentView(Model model, @RequestParam("bbsNo") int bbsNo) {
		List<BBSComment> commentListZero = BBSCommentRepositoy.selectComBybbsNo(bbsNo);
		model.addAttribute("commentListZero", commentListZero);
		return "redirect:/BBS/BBSlist/{bbsNo}";
	}
	
//	@PostMapping("/write")
//	BBSComment bbsComItem = BBSCommentRepositoy.selectComBybbsNo(bbsNo);
}
