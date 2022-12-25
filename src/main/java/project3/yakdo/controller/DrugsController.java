/**
 * 약품 페이지 컨트롤러
 * 담당: 홍준표
 */
package project3.yakdo.controller;

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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.DrugMark;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.search.FindDrugForm;
import project3.yakdo.service.drugs.search.FindDrugService;

@Slf4j
@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugsController {
	
	private final DrugsRepository drugsRepository;
	private final FindDrugService findDrugService;
	
	@GetMapping()
	public String drugsHomeGet(Model model) { // drugs 홈
		return "drugs/drugshome";
	}
	
	/**
	 * 약품 상세검색창, 결과창과 같은 HTML파일
	 * 홈검색은 일반검색, 상세검색은 이곳으로 오게 함.
	 * 상세검색은, 검색창에서 아코디언방식으로 할까 생각중
	 * 담당자 : 홍준표
	 */
	@GetMapping("/search")
	public String findDrug(Model model) { // 약품 상세검색
		FindDrugForm findDrugForm = new FindDrugForm();
		model.addAttribute("findDrugForm",findDrugForm);
		List<DrugMark> drugMarkList = drugsRepository.getDrugMarkAll();
		model.addAttribute("drugMarkList",drugMarkList);
		return "drugs/finddrug";
	}
	
	/**
	 * 약품 상세검색 결과창, 검색창과 같은 HTML 파일
	 * 담당자 : 홍준표
	 */
	@PostMapping("/search")
	public String findDrugResult(Model model,@ModelAttribute FindDrugForm findDrugForm) { // 약품 상세검색 결과
		List<DrugInfo> findDrugInfoList = findDrugService.findDrugResult(findDrugForm);
		model.addAttribute("findDrugInfoList",findDrugInfoList);
		return "drugs/finddrug";
	}
	
	/**
	 * 약품 상세정보페이지
	 * 회원정보와 대조하여 얼럿창 띄우기 기능 추가예정
	 * 담당자 : 홍준표
	 */
	@GetMapping("/info/{itemSeq}")
	public String drugInfo(Model model, @PathVariable("itemSeq") String itemSeq) { // 약품 상세정보
		DrugInfo drugInfo = drugsRepository.getDrugInfoByItemSeq(itemSeq);
		model.addAttribute("drugInfo",drugInfo);
		return "drugs/druginfo";
	}
	
	/**
	 * API로 db update 시작(POST로 "/drugs/apiUpdate" 경로 이동만 하면 API 로 DB업데이트 시작)
	 * 실제 동작은 "/drugs/dbupdate"에서 하게 되는데, 그동안 띄워 둘 화면
	 * 담당자 : 홍준표
	 */
	@PostMapping("/apiUpdate")
	public String dbUpdate() {
		return "drugs/startdbupdate";
	}
	
	/**
	 * API로 DB update를 실제 동작하고, 동작이 완료되면 완료페이지를 보여줌
	 * 담당자 : 홍준표
	 */
	@RequestMapping("/dbupdate")
	public String drugsHomePost(HttpServletRequest req) { //db update 완료
		findDrugService.dbUpdate(req);
		return "drugs/dbupdate";
	}

	
}
