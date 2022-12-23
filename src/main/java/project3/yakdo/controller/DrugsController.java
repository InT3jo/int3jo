/**
 * 약품 페이지 컨트롤러
 * 담당: 홍준표
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.api.DrugAPI;

@Slf4j
@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugsController {
	
	private final DrugsRepository drugsRepository;
	
	@GetMapping()
	public String drugsHomeGet(Model model) { // drugs 홈
		return "drugs/drugshome";
	}
	
	@GetMapping("/info/{itemSeq}")
	public String drugInfo(Model model, @PathVariable("itemSeq") String itemSeq) { // 약품 상세정보
		DrugInfo drugInfo = drugsRepository.getDrugInfoByItemSeq(itemSeq);
		model.addAttribute("drugInfo",drugInfo);
		return "drugs/druginfo";
	}
	
	@GetMapping("/list/{drugListPage}")
	public String drugList(Model model,@PathVariable("drugListPage") String drugListPage) { // 약품 전체 리스트(페이징)
		try {
			if(Integer.parseInt(drugListPage)<=0) {
				return "error/404";
			}else if(Integer.parseInt(drugListPage) > drugsRepository.getDrugInfoCountAll()){
				return "error/404";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "error/404";
		}
		List<DrugInfo> drugInfoList = drugsRepository.getDrugInfoList(drugListPage);
		model.addAttribute("drugInfoList",drugInfoList);
		model.addAttribute("drugInfoList",drugInfoList);
		return "drugs/listall";
	}
	
	@GetMapping("/list/nextPage/{nowPage}")
	public String drugNextPage(Model model,@PathVariable("nowPage") String nowPage) { // 리스트 다음페이지
		int nextPage = Integer.parseInt(nowPage) +1;
		return "redirect:/drugs/list/"+nextPage;
	}
	@GetMapping("/list/prevPage/{nowPage}")
	public String drugPrevPage(Model model,@PathVariable("nowPage") String nowPage) {// 리스트 이전페이지
		int prevPage = Integer.parseInt(nowPage) -1;
		return "redirect:/drugs/list/"+prevPage;
	}
	
	
	@PostMapping("")
	public String dbUpdate() { //db update 시작
		return "drugs/startdbupdate";
	}
	
	@RequestMapping("/dbupdate")
	public String drugsHomePost() { //db update 완료
		DrugAPI drugAPI = new DrugAPI(drugsRepository);
		drugAPI.getAPI();
		return "drugs/dbupdate";
	}
}
