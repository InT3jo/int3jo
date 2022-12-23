package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;

@Controller
public class JoinController {

	@GetMapping("/join")
	public String join (Model model) {
		Users users = new Users();
		UsersInfo usersInfo = new UsersInfo();
		
		model.addAttribute("users", users);
		model.addAttribute("usersInfo", usersInfo);
		return "/users/join/join";
	}
}
