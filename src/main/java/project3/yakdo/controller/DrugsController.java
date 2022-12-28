/**
 * 약품 페이지 컨트롤러
 * 담당: 홍준표
 */
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.domain.drugs.DrugMark;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.api.DrugAPIService;
import project3.yakdo.service.drugs.search.FindDrugForm;
import project3.yakdo.service.drugs.search.FindDrugService;

@Slf4j
@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugsController {
	
	private final DrugsRepository drugsRepository;
	private final FindDrugService findDrugService;
	private final DrugAPIService drugAPIService;
	
	/**
	 * 약품 상세검색창, 결과창과 같은 HTML파일
	 * 홈검색은 일반검색, 상세검색은 이곳으로 오게 함.
	 * 상세검색은, 검색창에서 아코디언방식으로 할까 생각중
	 * 담당자 : 홍준표
	 */
	@GetMapping("")
	public String findDrug(Model model) {
		FindDrugForm findDrugForm = new FindDrugForm();
		model.addAttribute("findDrugForm",findDrugForm);
		
		List<DrugMark> tempMarkList = drugsRepository.getDrugMarkAll();
		List<List<DrugMark>> drugMarkList = new ArrayList<>();
		List<Integer> drugMarkPage = new ArrayList<>(); 
		int divide = 40;
		int index = 0;
		int page = 1;
		for(int i=0;i<(tempMarkList.size()/divide)+1;i++) {
			List<DrugMark> tempList=new ArrayList<>();
			for(int j=0;j<divide;j++) {
				if(index >= tempMarkList.size()) {
					break;
				}
				tempList.add(tempMarkList.get(index));
				index++;
			}
			drugMarkList.add(tempList);
			drugMarkPage.add(page++);
		}
		model.addAttribute("drugMarkList",drugMarkList);
		model.addAttribute("drugMarkPage",drugMarkPage);
		
		model.addAttribute("findMoreStyle","display:inline-block;");
		return "drugs/finddrug";
	}
	
	/**
	 * 약품 상세검색 결과창, 검색창과 같은 HTML 파일
	 * 담당자 : 홍준표
	 */
	@PostMapping("")
	public String findDrugResult(Model model,@ModelAttribute FindDrugForm findDrugForm,HttpServletRequest req) {
		List<DrugInfo> findDrugInfoList = new ArrayList<>();
		if(req.getParameter("searchAll") != null) {
			findDrugForm.setItemName(req.getParameter("searchAll"));
			findDrugForm.setIngrName(req.getParameter("searchAll"));
			findDrugForm.setEntpName(req.getParameter("searchAll"));
			findDrugForm.setDrugShape("");
			findDrugForm.setDrugColor("");
			findDrugForm.setDrugPrint("");
			findDrugForm.setDrugLineFront("");
			findDrugForm.setDrugLineBack("");
			findDrugForm.setDrugMark("");
			findDrugInfoList = findDrugService.findDrugResultAll(findDrugForm);
		}else {
			findDrugInfoList = findDrugService.findDrugResult(findDrugForm);			
		}
		model.addAttribute("findDrugInfoList",findDrugInfoList);
		
		List<DrugMark> tempMarkList = drugsRepository.getDrugMarkAll();
		List<List<DrugMark>> drugMarkList = new ArrayList<>();
		List<Integer> drugMarkPage = new ArrayList<>(); 
		int divide = 40;
		int index = 0;
		int page = 1;
		for(int i=0;i<(tempMarkList.size()/divide)+1;i++) {
			List<DrugMark> tempList=new ArrayList<>();
			for(int j=0;j<divide;j++) {
				if(index >= tempMarkList.size()) {
					break;
				}
				tempList.add(tempMarkList.get(index));
				index++;
			}
			drugMarkList.add(tempList);
			drugMarkPage.add(page++);
		}
		model.addAttribute("drugMarkList",drugMarkList);
		
		model.addAttribute("findMoreStyle","display:none;");
		return "drugs/finddrug";
	}
	
	/**
	 * 약품 상세정보페이지
	 * 회원정보와 대조하여 얼럿창 띄우기 기능 추가예정
	 * 담당자 : 홍준표
	 */
	@GetMapping("/{itemSeq}")
	public String drugInfo(Model model, @PathVariable("itemSeq") String itemSeq) {
		DrugInfo drugInfo = drugsRepository.getDrugInfoByItemSeq(itemSeq);
		model.addAttribute("drugInfo",drugInfo);
		return "drugs/druginfo";
	}
	
	/**
	 * API로 db update 시작(POST로 "/drugs/apiUpdate" 경로 이동만 하면 API 로 DB업데이트 시작)
	 * 실제 동작은 "/drugs/dbupdate"에서 하게 되는데, 업데이트중 노출되는 화면
	 * 담당자 : 홍준표
	 */
	@PostMapping("/apiUpdate")
	public String dbUpdate(Model model) {
		LocalDateTime date = LocalDateTime.now();
		
		model.addAttribute("date",date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")));
		return "drugs/startdbupdate";
	}
	
	/**
	 * API로 DB update를 실제 동작하고, 동작이 완료되면 완료페이지를 보여줌
	 * 담당자 : 홍준표
	 */
	@RequestMapping("/dbupdate")
	public String drugsHomePost(HttpServletRequest req) { //db update 완료
		drugAPIService.getAPI(req);
		return "drugs/dbupdate";
	}

	
}
