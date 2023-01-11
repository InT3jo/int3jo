/**
 * 게시판 페이지 컨트롤러
 * 담당: 배고운
 */
package project3.yakdo.controller;

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
import project3.yakdo.domain.BBS.Reply;
import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.service.BBS.BBSComService;
import project3.yakdo.service.BBS.BBSService;
import project3.yakdo.service.users.LoginService;
import project3.yakdo.validation.BBSComValidator;
import project3.yakdo.validation.BBSValidator;
import project3.yakdo.validation.ReplyValidator;

@Slf4j
@Controller
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성
@RequestMapping("/BBS")
public class BBSController {


	private final BBSService bbsService;
	private final BBSComService bbsComService;
	private final LoginService loginService;


	/**
	 * 게시글 목록 출력 + 페이징 + 검색 
	 * 글번호 bbs_no에 해당하는 답글리스트 불러오기
	 * @param scri
	 * @param model
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/listSearch")
	public String BBSList(@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		// 전체 게시글 리스트 불러오기 
		model.addAttribute("list", bbsService.getBBSList(scri));
		// 전체 답글 리스트 불러오기
		model.addAttribute("listRe", bbsService.getReList());

		int count = bbsService.countSearch(scri);
		model.addAttribute("pageMaker", bbsService.makePage(scri,count));

		return "BBS/listSearch";
	}


	/**
	 * 게시글쓰기 insert
	 * @param model
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/BBSwrite")
	public String BBSwrite(Model model, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		model.addAttribute("BBS", new BBS());

		return "BBS/BBSwrite";
	}

	
	/**
	 * 게시글쓰기 insert
	 * @param bbs
	 * @param bindingResult
	 * @param model
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@PostMapping("/BBSwrite")
	public String newBBSInsertModel(@Validated @ModelAttribute BBS bbs, BindingResult bindingResult, Model model,
			HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		// 제목내용 입력안하면 다시 글쓰는 페이지로 돌아감
		BBSValidator bbsValidator = new BBSValidator();
		bbsValidator.validate(bbs, bindingResult);

		if (bindingResult.hasErrors()) {
			return "BBS/BBSwrite";
		}

		bbsService.insertBBS(bbs);

		return "redirect:/BBS/listSearch";

	}

	
	/**
	 * 글번호에 해당하는 게시글 상세보기 
	 * @param model
	 * @param bbsNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@PostMapping("/BBSview")
	public String BBSview2(Model model, @RequestParam("bbsNo") int bbsNo, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		model.addAttribute("BBS", bbsService.getBBSbybbsNo(bbsNo));
		return "BBS/BBSview";
	}

	
	/**
	 * 글번호에 해당하는 게시글 상세보기 
	 * 글번호(bbsNo)에 해당하는 댓글 불러오기
	 * @param model
	 * @param bbsNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/BBSlist/{bbsNo}")
	public String BBSview(Model model, @PathVariable("bbsNo") Integer bbsNo, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		model.addAttribute("BBS", bbsService.getBBSbybbsNo(bbsNo));
		model.addAttribute("commentListZero", bbsComService.BBSComListbybbsNo(bbsNo));
		model.addAttribute("BBSComment", new BBSComment());

		return "BBS/BBSview";
	}


	/**
	 * 댓글 작성
	 * @param model
	 * @param bbsNo
	 * @param bbsComment
	 * @param bindingResult
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@PostMapping("/BBSlist/{bbsNo}")
	public String insertBBSCom(Model model, @PathVariable("bbsNo") Integer bbsNo, @ModelAttribute BBSComment bbsComment,
			BindingResult bindingResult, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		model.addAttribute("commentListZero", bbsComService.BBSComListbybbsNo(bbsNo));
		model.addAttribute("BBS", bbsService.getBBSbybbsNo(bbsNo));

		// 댓글 내용 입력안하면 다시 댓글쓰는 페이지로 돌아감
		BBSComValidator bbsComValidator = new BBSComValidator();
		bbsComValidator.validate(bbsComment, bindingResult);

		if (bindingResult.hasErrors()) {
			return "redirect:/BBS/BBSlist/{bbsNo}";
		}

		bbsComService.insertBBSCom(bbsComment);
//		model.addAttribute("BBSCom", new BBSComment());

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}


	/**
	 * 게시글 수정 
	 * @param model
	 * @param bbsNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/update/{bbsNo}") // 어떤 bbsNo에 대한 업데이트를 할거냐
	public String updateBBS(Model model, @PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		model.addAttribute("BBS", bbsService.getBBSbybbsNo(bbsNo));
		return "BBS/BBSupdate.html";
	}


	/**
	 * 게시글 수정 
	 * @param model
	 * @param bbsNo
	 * @param bbs
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@PostMapping("/update/{bbsNo}")
	public String updateBBSprocess(Model model, @PathVariable("bbsNo") int bbsNo, @ModelAttribute BBS bbs,
			HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		bbsService.updateBBS(bbsNo, bbs);

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	/**
	 * 게시글 본인 삭제 
	 * @param model
	 * @param bbsNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@RequestMapping("/delete/{bbsNo}")
	public String updateShowOneBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		bbsService.updateShowOneBybbsNo(bbsNo);
		return "redirect:/BBS/listSearch";

	}



	/**
	 * 게시글 관리자 삭제 
	 * @param model
	 * @param bbsNo
	 * @param req
	 * @return
	 */
	@RequestMapping("/adminDelete/{bbsNo}")
	public String updateShowTwoBybbsNo(Model model, @PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		bbsService.updateShowTwoBybbsNo(bbsNo);
		return "redirect:/BBS/listSearch";

	}

	

	
	/**
	 * 댓글 본인 삭제 
	 * @param model
	 * @param bbsNo
	 * @param comNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@RequestMapping("/deleteCom/{bbsNo}/{comNo}")
	public String updateComShowOneBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo,
			@PathVariable("comNo") Integer comNo, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		
		bbsComService.deleteCom(bbsNo, comNo);
		

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}

	
	/**
	 * 댓글 수정 
	 * @param model
	 * @param bbsNo
	 * @param comNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/updateCom/{bbsNo}/{comNo}")
	public String updateCom(Model model, @PathVariable("bbsNo") Integer bbsNo, @PathVariable("comNo") int comNo,
			HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		model.addAttribute("bbsComment", bbsComService.getComment(bbsNo, comNo));
		
		return "BBS/updateCom";
	}


	/**
	 * 댓글 수정 
	 * @param model
	 * @param bbsNo
	 * @param comNo
	 * @param bbsComment
	 * @param bindingResult
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@PostMapping("/updateCom/{bbsNo}/{comNo}")
	public String updateComProcess(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("comNo") int comNo,
			@ModelAttribute BBSComment bbsComment, BindingResult bindingResult, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		// 댓글 내용 입력안하면 다시 댓글쓰는 페이지로 돌아감
		BBSComValidator bbsComValidator = new BBSComValidator();
		bbsComValidator.validate(bbsComment, bindingResult);

		if (bindingResult.hasErrors()) {
			return "redirect:/BBS/BBSlist/{bbsNo}";
		}

		bbsComService.updateCom(bbsNo, comNo, bbsComment);

		return "redirect:/BBS/BBSlist/{bbsNo}";
	}


	/**
	 * 댓글 관리자 삭제 
	 * @param model
	 * @param bbsNo
	 * @param comNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@RequestMapping("/deleteComAdmin/{bbsNo}/{comNo}")
	public String deleteComAdmin(Model model, @PathVariable("bbsNo") Integer bbsNo,
			@PathVariable("comNo") Integer comNo, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		bbsComService.deleteComByAdmin(bbsNo, comNo);

		return "redirect:/BBS/BBSlist/{bbsNo}";

	}

	
	/**
	 * 게시글 복구
	 * @param model
	 * @param bbsNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@RequestMapping("/recover/{bbsNo}")
	public String updateShowZeroBybbsNo(Model model, @PathVariable("bbsNo") Integer bbsNo, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		bbsService.recoverBBSBybbsNo(bbsNo);
		return "redirect:/admin/adminBBSlist";
	}


	/**
	 * 답변 상세보기 
	 * @param model
	 * @param bbsNo
	 * @param reNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/replyView2/{bbsNo}/{reNo}")
	public String replyView2(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("reNo") int reNo,
			HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		
		model.addAttribute("Reply", bbsService.viewReply(bbsNo, reNo));

		return "BBS/replyView2";
	}


	/**
	 * 답변 수정 
	 * @param model
	 * @param bbsNo
	 * @param reNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/updateRe2/{bbsNo}/{reNo}")
	public String updateRe2(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("reNo") int reNo,
			HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		model.addAttribute("Reply", bbsService.viewReply(bbsNo, reNo));

		return "BBS/updateReply2";
	}

	
	/**
	 * 답변 수정 
	 * @param model
	 * @param bbsNo
	 * @param reNo
	 * @param reply
	 * @param bindingResult
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@PostMapping("/updateRe2/{bbsNo}/{reNo}")
	public String updateRe2Process(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("reNo") int reNo,
			@ModelAttribute Reply reply, BindingResult bindingResult, HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		// 내용 입력안하면 다시 글쓰는 페이지로 돌아감
		ReplyValidator bbsValidator = new ReplyValidator();
		bbsValidator.validate(reply, bindingResult);

		if (bindingResult.hasErrors()) {
			return "BBS/updateReply2";
		}

		bbsService.updateReply(bbsNo, reNo, reply);

		return "redirect:/BBS/replyView2/{bbsNo}/{reNo}";
	}


	/**
	 * 답변 삭제 
	 * @param model
	 * @param bbsNo
	 * @param reNo
	 * @param req
	 * @return
	 */
	@RequestMapping("/deleteRe/{bbsNo}/{reNo}")
	public String deleteRe(Model model, @PathVariable("bbsNo") int bbsNo, @PathVariable("reNo") int reNo,
			HttpServletRequest req) {
		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		bbsService.deleteReply(bbsNo, reNo);

		return "redirect:/BBS/listSearch";

	}
	

	/**
	 * 게시판 관리자 답변 쓰기 
	 * @param model
	 * @param bbsNo
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@GetMapping("/writeAnswer/{bbsNo}")
	public String BBSwrite(Model model,@PathVariable("bbsNo") int bbsNo, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));
		
		model.addAttribute("BBS", bbsService.getBBSbybbsNo(bbsNo));
		
		model.addAttribute("Reply", new Reply());
		
		return "BBS/writeAnswer";
	}
	
	 
	/**
	 * 게시판 관리자 답변 쓰기 
	 * @param reply
	 * @param bindingResult
	 * @param bbs
	 * @param bbsNo
	 * @param model
	 * @param req
	 * @return
	 * 담당자 : 배고운 
	 */
	@PostMapping("/writeAnswer/{bbsNo}")
	public String newBBSInsertModel(@ModelAttribute Reply reply,BindingResult bindingResult,@ModelAttribute BBS bbs, @PathVariable("bbsNo") int bbsNo,Model model, HttpServletRequest req) {
		// 현재 주소정보
		model.addAttribute("uriHere", req.getRequestURI());

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		model.addAttribute("user", loginService.getLoginUser(req));

		// 내용 입력안하면 다시 글쓰는 페이지로 돌아감
		ReplyValidator bbsValidator = new ReplyValidator();
		bbsValidator.validate(reply, bindingResult);

		if (bindingResult.hasErrors()) {
			return "BBS/writeAnswer/" + bbsNo;
		}

		bbsService.insertReply(reply);

		return "redirect:/BBS/listSearch";

	}

}
