package project3.yakdo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.DrugMark;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.api.DrugAPIService;
import project3.yakdo.service.drugs.search.FindDrugForm;
import project3.yakdo.service.drugs.search.FindDrugService;
import project3.yakdo.service.drugs.temp.UserService;
import project3.yakdo.session.SessionVar;

@Slf4j // 마무리작업단계에서 제거예정
@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugsController {

	private final DrugsRepository drugsRepository;
	private final FindDrugService findDrugService;
	private final DrugAPIService drugAPIService;
	private final UserService userService;

	/**
	 * 약품 상세검색창 담당자 : 홍준표
	 */
	@GetMapping("")
	public String findDrug(Model model, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = userService.getLoginUser(req);
		model.addAttribute("user", user);

		// 검색할 내용(폼)
		FindDrugForm findDrugForm = new FindDrugForm();
		model.addAttribute("findDrugForm", findDrugForm);

		// 검색할 마크정보
		List<List<DrugMark>> drugMarkList = new ArrayList<>();
		List<Integer> drugMarkPage = new ArrayList<>();
		findDrugService.setMarkListAndMarkPage(drugMarkList, drugMarkPage);
		model.addAttribute("drugMarkList", drugMarkList);
		model.addAttribute("drugMarkPage", drugMarkPage);

		// GetMapping이면 상세검색 창 보임
		model.addAttribute("findMoreStyle", "display:inline-block;");
		return "drugs/finddrug";
	}

	/**
	 * 약품 검색결과창 담당자 : 홍준표
	 */
	@PostMapping("")
	public String findDrugResult(Model model, @ModelAttribute FindDrugForm findDrugForm, HttpServletRequest req) {

		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = userService.getLoginUser(req);
		model.addAttribute("user", user);

		// 폼에 따른 검사결과를 리스트방식으로 받아옴
		List<DrugInfo> findDrugInfoList = findDrugService.setFindDrugInfoList(findDrugForm, req);
		model.addAttribute("findDrugInfoList", findDrugInfoList);

		// 검색할 마크정보
		List<List<DrugMark>> drugMarkList = new ArrayList<>();
		List<Integer> drugMarkPage = new ArrayList<>();
		findDrugService.setMarkListAndMarkPage(drugMarkList, drugMarkPage);
		model.addAttribute("drugMarkList", drugMarkList);

		// PostMapping이면 상세검색 창 안보임
		model.addAttribute("findMoreStyle", "display:none;");
		return "drugs/finddrug";
	}

	/**
	 * 약품 상세정보페이지 회원정보와 대조하여 얼럿창 띄우기 기능 추가예정 담당자 : 홍준표
	 */
	@GetMapping("/{itemSeq}")
	public String drugInfo(Model model, @PathVariable("itemSeq") String itemSeq, HttpServletRequest req) {
		// 현재 주소정보
		String uriHere = req.getRequestURI();
		model.addAttribute("uriHere", uriHere);

		// 로그인된 유저정보(로그인되어있지 않다면 null)
		Users user = userService.getLoginUser(req);
		model.addAttribute("user", user);

		// 선택된 약물정보
		DrugInfo drugInfo = drugsRepository.getDrugInfoByItemSeq(itemSeq);
		model.addAttribute("drugInfo", drugInfo);
		return "drugs/druginfo";
	}

	/**
	 * API로 db update 시작(POST로 "/drugs/apiUpdate" 경로 이동만 하면 API 로 DB업데이트 시작) 실제 동작은
	 * "/drugs/dbupdate"에서 하게 되는데, 업데이트중 노출되는 화면 담당자 : 홍준표
	 */
	@PostMapping("/apiUpdate")
	public String dbUpdate(Model model, HttpServletRequest req) {
		LocalDateTime date = LocalDateTime.now();

		// 점검중 세션 추가
		HttpSession session = req.getSession();
		session.setAttribute(SessionVar.INSPECTION, "점검중");

		model.addAttribute("date", date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")));
		return "drugs/startdbupdate";
	}

	/**
	 * API로 DB update를 실제 동작하고, 동작이 완료되면 완료페이지를 보여줌 담당자 : 홍준표
	 */
	@RequestMapping("/dbupdate")
	public String drugsHomePost(HttpServletRequest req) {
		drugAPIService.getAPI(req);

		// 점검중 세션 제거
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute(SessionVar.INSPECTION);
		}

		return "drugs/dbupdate";
	}

}
