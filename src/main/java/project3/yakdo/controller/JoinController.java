package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.service.users.JoinService;
import project3.yakdo.validation.form.JoinForm;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

	private final JoinService joinService;
	
	@GetMapping("/join")
	public String join (Model model) {
		JoinForm joinForm = new JoinForm();
		model.addAttribute("joinForm", joinForm);
		
		return "/users/join/join";
	}

	@PostMapping("/join")
	public String doJoin (@ModelAttribute JoinForm joinForm
						, Model model) {
		joinService.join(joinForm);
		return "/home";
	}
}
