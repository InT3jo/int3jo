/**
 * 약품 페이지 컨트롤러
 * 담당: 홍준표
 */
package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project3.yakdo.repository.DrugsRepository;
import project3.yakdo.service.drugs.api.DrugAPI;

@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugsController {
	
	private final DrugsRepository drugsRepository;
	
	@GetMapping()
	public String drugsHomeGet(Model model) {
		return "drugs/drugshome";
	}
	@PostMapping()
	public String drugsHomePost() {
		DrugAPI drugAPI = new DrugAPI(drugsRepository);
		drugAPI.getAPI();
		return "drugs/drugshome";
	}
}
