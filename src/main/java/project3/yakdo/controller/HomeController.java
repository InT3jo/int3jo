package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home(Model model
					, @CookieValue(name="userNo", required=false) Integer userNo
					, @CookieValue(name="userEmail", required=false) String userEmail) {
		
		log.info("userNo={}, userEmail={}", userNo, userEmail);
		
		return "/home";
	}
}
