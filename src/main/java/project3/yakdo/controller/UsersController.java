/**
 * 유저 페이지 컨트롤러
 * 담당: 빙예은
 */
package project3.yakdo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.mybatis.UsersMybatisRepository;
import project3.yakdo.session.SessionVar;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class UsersController {
	private final UsersMybatisRepository usersMybatisRepository;
	
	/**
	 * TEST
	 * userList를 화면에 띄울 메소드
	 * @param model
	 * @return
	 */
	@GetMapping("/userList")
	public String showUser(Model model) {
		List<Users> userList = usersMybatisRepository.selectAllUsers();
		log.info(userList.toString());
		model.addAttribute("userList", userList);
		return "/users/login/userList";
	}
	
	@GetMapping
	public String Users(Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		if(session == null || session.getAttribute(SessionVar.LOGIN_MEMBER) == null) {
			return "redirect:/";
		}
		
		return "/users/login/userList";
	}
	
}
