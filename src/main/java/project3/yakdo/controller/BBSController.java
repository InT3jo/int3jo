/**
 * 게시판 페이지 컨트롤러
 * 담당: 배고운
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.BBSComment;
import project3.yakdo.domain.BBS.PageMaker;
import project3.yakdo.domain.BBS.Reply;
import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.BBSCommentRepository;
import project3.yakdo.repository.BBSRepository;
import project3.yakdo.service.users.LoginService;
import project3.yakdo.validation.BBSComValidator;
import project3.yakdo.validation.BBSValidator;
import project3.yakdo.validation.ReplyValidator;

@Slf4j
@Controller
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/BBS")
public class BBSController {

	private final BBSRepository BBSRepository;
	private final BBSCommentRepository bbsCommentRepositoy;
	private final LoginService loginService;

	// 게시글 목록 출력 + 페이징 추가 + 검색 추가 - 최종 게시판 검색+페이징 다 됨
	// 글번호 bbs_no에 해당하는 답글 불러오기(추가 01-05-14:08)
	@GetMapping("/listSearch")
	public String BBSList(@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		List<BBS> list = BBSRepository.listSearch(scri);
		model.addAttribute("list", list);

		// 전체 답글 리스트 불러오기
		List<Reply> listRe = BBSRepository.listRe();
		model.addAttribute("listRe", listRe);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(BBSRepository.countSearch(scri));
		model.addAttribute("pageMaker", pageMaker);

		return "BBS/listSearch";
	}

	// 게시글쓰기 insert
	@GetMapping("/BBSwrite")
	public String BBSwrite(Model model, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		model.addAttribute("BBS", new BBS());

		return "BBS/BBSwrite";
	}

	// 게시글쓰기 insert
	@PostMapping("/BBSwrite")
	public String newBBSInsertModel(@Validated @ModelAttribute BBS bbs, BindingResult bindingResult, Model model,
			HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		// 제목내용 입력안하면 다시 글쓰는 페이지로 돌아감
		BBSValidator bbsValidator = new BBSValidator();
		bbsValidator.validate(bbs, bindingResult);

		if (bindingResult.hasErrors()) {
			return "BBS/BBSwrite";
		}

		BBSRepository.insertBBS(bbs);

		return "redirect:/BBS/listSearch";

	}

	// 게시글 상세보기 selectBybbsNo
	@PostMapping("/BBSview")
	public String BBSview2(Model model, @RequestParam("bbsNo") int bbsNo, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSview";
	}

	// 게시글 상세보기 selectBybbsNo
	// 글번호(bbsNo)에 해당하는 댓글 불러오기
	@GetMapping("/BBSlist/{bbsNo}")
	public String BBSview(Model model, @PathVariable("bbsNo") Integer bbsNo, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		List<BBSComment> commentListZero = bbsCommentRepositoy.selectComBybbsNo(bbsNo);
		model.addAttribute("commentListZero", commentListZero);
		model.addAttribute("BBSComment", new BBSComment());

		return "BBS/BBSview";
	}

	// 댓글 쓰기
	@PostMapping("/BBSlist/{bbsNo}")
	public String insertBBSCom(Model model, @PathVariable("bbsNo") Integer bbsNo, @ModelAttribute BBSComment bbsComment,
			BindingResult bindingResult, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		List<BBSComment> commentListZero = bbsCommentRepositoy.selectComBybbsNo(bbsNo);

		model.addAttribute("commentListZero", commentListZero);
		model.addAttribute("BBS", bbsItem);

		// 댓글 내용 입력안하면 다시 댓글쓰는 페이지로 돌아감
		BBSComValidator bbsComValidator = new BBSComValidator();
		bbsComValidator.validate(bbsComment, bindingResult);

		if (bindingResult.hasErrors()) {
			return "redirect:/BBS/BBSlist/{bbsNo}";
		}

		bbsCommentRepositoy.insertBBSCom(bbsComment);
//		model.addAttribute("BBSCom", new BBSComment());

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 게시글 수정
	@GetMapping("/update/{bbsNo}") // 어떤 bbsNo에 대한 업데이트를 할거냐
	public String updateBBS(Model model, @PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		BBS bbsItem = BBSRepository.selectBybbsNo(bbsNo);
		model.addAttribute("BBS", bbsItem);
		return "BBS/BBSupdate.html";
	}

	// 게시글 수정
	@PostMapping("/update/{bbsNo}")
	public String updateBBSprocess(Model model, @PathVariable("bbsNo") int bbsNo, @ModelAttribute BBS bbs,
			HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		BBSRepository.updateBBS(bbsNo, bbs);

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 게시글 본인삭제
	@GetMapping("/delete/{bbsNo}")
	public String updateShowOneBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		BBSRepository.updateShowOneBybbsNo(bbsNo);
		return "redirect:/BBS/listSearch";

	}

	// 게시글 본인삭제
	@PostMapping("/delete/{bbsNo}")
	public String updateShowOneBybbsNoProcess(Model model, @PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		BBSRepository.updateShowOneBybbsNo(bbsNo);
		return "redirect:/BBS/listSearch";

	}

	// 게시글 관리자 삭제
	@GetMapping("/adminDelete/{bbsNo}")
	public String updateShowTwoBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		BBSRepository.updateShowTwoBybbsNo(bbsNo);
		return "redirect:/BBS/listSearch";

	}

	// 게시글 관리자 삭제
	@PostMapping("/adminDelete/{bbsNo}")
	public String updateShowTwoBybbsNoProcess(Model model, @PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		BBSRepository.updateShowTwoBybbsNo(bbsNo);
		return "redirect:/BBS/listSearch";

	}

	// 댓글 본인 삭제
	@RequestMapping("/deleteCom/{bbsNo}/{comNo}")
	public String updateComShowOneBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo,
			@PathVariable("comNo") Integer comNo, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		if (bbsCommentRepositoy.updateComShowOneByBbsNo(bbsNo, comNo)) {

		} else {

		}

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 댓글 수정
	@GetMapping("/updateCom/{bbsNo}/{comNo}")
	public String updateCom(Model model, @PathVariable("bbsNo") Integer bbsNo, @PathVariable("comNo") int comNo,
			HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		BBSComment comItem = bbsCommentRepositoy.selectOneCom(bbsNo, comNo);
		model.addAttribute("bbsComment", comItem);
		log.info("콤아이템 {}", comItem);

		return "BBS/updateCom";
	}

	// 댓글 수정
	@PostMapping("/updateCom/{bbsNo}/{comNo}")
	public String updateComProcess(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("comNo") int comNo,
			@ModelAttribute BBSComment bbsComment, BindingResult bindingResult, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		// 댓글 내용 입력안하면 다시 댓글쓰는 페이지로 돌아감
		BBSComValidator bbsComValidator = new BBSComValidator();
		bbsComValidator.validate(bbsComment, bindingResult);

		if (bindingResult.hasErrors()) {
			return "redirect:/BBS/BBSlist/{bbsNo}";
		}

		bbsCommentRepositoy.updateCom(bbsNo, comNo, bbsComment);

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	// 댓글 관리자 삭제
	@RequestMapping("/deleteComAdmin/{bbsNo}/{comNo}")
	public String deleteComAdmin(Model model, @PathVariable("bbsNo") Integer bbsNo,
			@PathVariable("comNo") Integer comNo, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		bbsCommentRepositoy.updateComShowTwoBybbsNo(bbsNo, comNo);

		return "redirect:/BBS/BBSlist/{bbsNo}";

	}

	// 게시글 복구
	@RequestMapping("/recover/{bbsNo}")
	public String updateShowZeroBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		BBSRepository.updateShowZeroBybbsNo(bbsNo);
		return "redirect:/admin/adminBBSlist";
	}

	// 답변 상세보기 2

	@GetMapping("/replyView2/{bbsNo}/{reNo}")
	public String replyView2(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("reNo") int reNo,
			HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		Reply reply = BBSRepository.replyView2(bbsNo, reNo);
		model.addAttribute("Reply", reply);

		return "BBS/replyView2";
	}

	// 답변 수정 2
	@GetMapping("/updateRe2/{bbsNo}/{reNo}")
	public String updateRe2(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("reNo") int reNo,
			HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		Reply replyItem = BBSRepository.replyView2(bbsNo, reNo);
		model.addAttribute("Reply", replyItem);

		return "BBS/updateReply2";
	}

	// 답변 수정 2
	@PostMapping("/updateRe2/{bbsNo}/{reNo}")
	public String updateRe2Process(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("reNo") int reNo,
			@ModelAttribute Reply reply, BindingResult bindingResult, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		// 내용 입력안하면 다시 글쓰는 페이지로 돌아감
		ReplyValidator bbsValidator = new ReplyValidator();
		bbsValidator.validate(reply, bindingResult);

		if (bindingResult.hasErrors()) {
			return "redirect:/BBS/listSearch";
		}

		BBSRepository.updateRe2(bbsNo, reNo, reply);

		return "redirect:/BBS/listSearch";
	}

	// 답변 삭제
	@RequestMapping("/deleteRe/{bbsNo}/{reNo}")
	public String deleteRe(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("reNo") int reNo,
			HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = loginService.getLoginUser(req);
		model.addAttribute("user", user);

		BBSRepository.updateReShow1(bbsNo, reNo);

		return "redirect:/BBS/listSearch";

	}

}
