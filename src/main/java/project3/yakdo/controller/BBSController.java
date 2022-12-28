/**
 * 게시판 페이지 컨트롤러
 * 담당: 배고운
 */
package project3.yakdo.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.BBSComment;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.BBSCommentRepository;
import project3.yakdo.repository.BBSRepository;
import project3.yakdo.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/BBS")
public class BBSController {

	private final BBSRepository BBSRepository;
	private final BBSCommentRepository bbsCommentRepositoy;

	// 게시글 목록 출력
	@GetMapping("/BBSlist")
	public String BBSList(Model model) {
		List<BBS> bbsListZero = BBSRepository.selectByShowZero();
		log.info("bbsListZero {}", bbsListZero);
		model.addAttribute("bbsListZero", bbsListZero);
		return "BBS/BBSlist";
	}

	// 게시글쓰기 insert
	@GetMapping("/BBSwrite")
	public String BBSwrite(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		// 쿠키를 통해 넘어온 userEmail이 없는 경우
		if (session == null) {
			return "/home";
		}
		//session 정보 출력해보기
		Enumeration<String> sessionNames = session.getAttributeNames();
		while (sessionNames.hasMoreElements()) {
			String name = sessionNames.nextElement();

			log.info("session {}, {}", name, session.getAttribute(name));
		}

		Users user = (Users) session.getAttribute(SessionVar.LOGIN_MEMBER);
		log.info("user객쳇 {}",user);
		if (user == null) {
			return "/home";
		}

		model.addAttribute("user", user);
		model.addAttribute("BBS", new BBS());
		return "BBS/BBSwrite";
	}

	// 게시글쓰기 insert
	@PostMapping("/BBSwrite")
	public String newBBSInsertModel(@ModelAttribute BBS bbs
			, Model model,HttpServletRequest req
		) {
		HttpSession session = req.getSession(false);

		// 쿠키를 통해 넘어온 userEmail이 없는 경우
		if (session == null) {
			return "/home";
		}
		//session 정보 출력해보기
		Enumeration<String> sessionNames = session.getAttributeNames();
		while (sessionNames.hasMoreElements()) {
			String name = sessionNames.nextElement();

			log.info("session {}, {}", name, session.getAttribute(name));
		}

		Users user = (Users) session.getAttribute(SessionVar.LOGIN_MEMBER);
		log.info("user객쳇 {}",user);
		if (user == null) {
			return "/home";
		}

		model.addAttribute("user", user);
		BBSRepository.insertBBS(bbs);
		
		return "redirect:/BBS/BBSlist";

	}

	// 게시글 읽기 selectBybbsNo
	@PostMapping("/BBSview")
	public String BBSview2(Model model, @RequestParam("bbsNo") int bbsNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSview";
	}

	// 게시글 읽기 selectBybbsNo
	// 글번호(bbsNo)에 해당하는 댓글 불러오기
	@GetMapping("/BBSlist/{bbsNo}")
	public String BBSview(Model model, @PathVariable("bbsNo") Integer bbsNo
			,HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		// 쿠키를 통해 넘어온 userEmail이 없는 경우
		if (session == null) {
			return "/home";
		}
		//session 정보 출력해보기
		Enumeration<String> sessionNames = session.getAttributeNames();
		while (sessionNames.hasMoreElements()) {
			String name = sessionNames.nextElement();

			log.info("session {}, {}", name, session.getAttribute(name));
		}

		Users user = (Users) session.getAttribute(SessionVar.LOGIN_MEMBER);
		log.info("user객쳇 {}",user);
		if (user == null) {
			return "/home";
		}

		model.addAttribute("user", user);
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		List<BBSComment> commentListZero = bbsCommentRepositoy.selectComBybbsNo(bbsNo);
		model.addAttribute("commentListZero", commentListZero);
		model.addAttribute("BBS", bbsItem);
		model.addAttribute("BBSComment", new BBSComment());

		return "BBS/BBSview";
	}

	// 댓글 쓰기
	@PostMapping("/BBSlist/{bbsNo}")
	public String insertBBSCom(Model model, @PathVariable("bbsNo") Integer bbsNo,
			@ModelAttribute BBSComment bbsComment) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		List<BBSComment> commentListZero = bbsCommentRepositoy.selectComBybbsNo(bbsNo);

		model.addAttribute("commentListZero", commentListZero);
		model.addAttribute("BBS", bbsItem);

		bbsCommentRepositoy.insertBBSCom(bbsComment);
//		model.addAttribute("BBSCom", new BBSComment());

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 게시글 수정
	@GetMapping("/update/{bbsNo}") // 어떤 bbsNo에 대한 업데이트를 할거냐
	public String updateBBS(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSupdate.html";
	}

	// 게시글 수정
	@PostMapping("/update/{bbsNo}")
	public String updateBBSprocess(Model model, @PathVariable("bbsNo") int bbsNo, @ModelAttribute BBS bbs) {

		BBSRepository.updateBBS(bbsNo, bbs);

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 게시글 본인삭제
	@GetMapping("/delete/{bbsNo}")
	public String updateShowOneBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo) {

		BBSRepository.updateShowOneBybbsNo(bbsNo);
		return "redirect:/BBS/BBSlist";

	}

	// 게시글 본인삭제
	@PostMapping("/delete/{bbsNo}")
	public String updateShowOneBybbsNoProcess(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBSRepository.updateShowOneBybbsNo(bbsNo);
		return "redirect:/BBS/BBSlist";

	}

	// 게시글 관리자 삭제
	@GetMapping("/adminDelete/{bbsNo}")
	public String updateShowTwoBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBSRepository.updateShowTwoBybbsNo(bbsNo);
		return "redirect:/BBS/BBSlist";

	}

	// 게시글 관리자 삭제
	@PostMapping("/adminDelete/{bbsNo}")
	public String updateShowTwoBybbsNoProcess(Model model, @PathVariable("bbsNo") int bbsNo) {
		BBSRepository.updateShowTwoBybbsNo(bbsNo);
		return "redirect:/BBS/BBSlist";

	}

// 	주석처리하고 comSeq넣어서 Test 중  
	// 댓글 본인 삭제
	@RequestMapping("/deleteCom/{bbsNo}/{comNo}")
	public String updateComShowOneBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo,
			@PathVariable("comNo") Integer comNo) {
		if (bbsCommentRepositoy.updateComShowOneByBbsNo(bbsNo, comNo)) {

		} else {

		}

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 댓글 본인 삭제
//		@PostMapping("/deleteCom/{bbsNo}/{comNo}")
//		public String updateComShowOneBybbsNoProcess(Model model
//					, @PathVariable("bbsNo") int bbsNo
//					, @PathVariable("comNo") int comNo) {
////			log.info("bbsno"+bbsNo.toString());
////			log.info("comno"+comNo);
//			
//			bbsCommentRepositoy.updateComShowOneBybbsNo(bbsNo,comNo);
//			
//			return "redirect:/BBS/BBSlist/{bbsNo}";
//		}

//		시퀀스로 test 하던거 
	// 댓글 본인 삭제
	/*
	 * @PostMapping("/deleteCom/{comSeq}") public String
	 * updateComShowOneBybbsNoProcess(Model model , @PathVariable("comSeq") int
	 * comSeq) { // log.info("bbsno"+bbsNo.toString()); // log.info("comno"+comNo);
	 * bbsCommentRepositoy.updateComShowOneBybbsNo(comSeq); return
	 * "redirect:/BBS/BBSlist/{bbsNo}"; }
	 */

	/*
	 * 댓글 수정 나중에 보완 22-12-28-11:41 //댓글수정
	 * 
	 * @GetMapping("/updateCom/{bbsNo}/{comNo}") public String updateCom(Model
	 * model, @PathVariable("bbsNo") Integer bbsNo) { BBS bbsItem =
	 * BBSRepository.selectBybbsNo(bbsNo); List<BBSComment> commentListZero =
	 * bbsCommentRepositoy.selectComBybbsNo(bbsNo);
	 * 
	 * model.addAttribute("commentListZero", commentListZero);
	 * model.addAttribute("BBS", bbsItem);
	 * 
	 * model.addAttribute("BBSComment", new BBSComment());
	 * 
	 * return"BBS/ComUpdate";
	 * 
	 * }
	 * 
	 * //댓글 수정
	 * 
	 * @PostMapping("/updateCom/{bbsNo}/{comNo}") public String
	 * updateComProcess(Model model,@PathVariable("bbsNo") int bbsNo
	 * ,@PathVariable("comNo") int comNo ,@ModelAttribute BBSComment bbsComment) {
	 * bbsCommentRepositoy.updateCom(bbsNo, bbsComment); return "BBS/ComUpdate"; }
	 */

	// 댓글 수정
	@GetMapping("/updateCom/{bbsNo}/{comNo}")
	public String updateCom(Model model, @PathVariable("bbsNo") Integer bbsNo, @PathVariable("comNo") int comNo) {
		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		List<BBSComment> commentListZero = bbsCommentRepositoy.selectComBybbsNo(bbsNo);

		model.addAttribute("commentListZero", commentListZero);
		model.addAttribute("BBS", bbsItem);

		model.addAttribute("BBSComment", new BBSComment());

		BBSComment comItem = bbsCommentRepositoy.selectOneCom(bbsNo, comNo);
		model.addAttribute("bbsComment", comItem);

		return "BBS/updateCom";

	}

	// 댓글 수정
	@PostMapping("/updateCom/{bbsNo}/{comNo}")
	public String updateComProcess(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("comNo") int comNo,
			@ModelAttribute BBSComment bbsComment) {
		bbsCommentRepositoy.updateCom(bbsNo, comNo, bbsComment);
		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

}
