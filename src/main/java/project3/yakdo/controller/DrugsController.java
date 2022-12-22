/**
 * 약품 페이지 컨트롤러
 * 담당: 홍준표
 */
package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.drugs.DrugInfo;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.api.DrugAPI;

@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugsController {
	
	private final DrugsRepository drugsRepository;
	
	@GetMapping() // drugs 홈
	public String drugsHomeGet(Model model) {
		return "drugs/drugshome";
	}
	
	@GetMapping("/info/{itemSeq}") // 약품 상세정보
	public String drugInfo(Model model, @PathVariable("itemSeq") String itemSeq) {
		DrugInfo drugInfo = drugsRepository.getDrugInfoByItemSeq(itemSeq);
		model.addAttribute("drugInfo",drugInfo);
		return "drugs/druginfo";
	}
	
	@PostMapping("")//db update 시작
	public String dbUpdate() {
		return "drugs/startdbupdate";
	}
	
	@RequestMapping("/dbupdate")//db 업데이트 실행 후, 완료페이지 보여주기
	public String drugsHomePost() {
		DrugAPI drugAPI = new DrugAPI(drugsRepository);
		drugAPI.getAPI();
		return "drugs/dbupdate";
	}
}
