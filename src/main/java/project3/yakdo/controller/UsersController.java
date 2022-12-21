/**
 * 유저 페이지 컨트롤러
 * 담당: 빙예은
 */
package project3.yakdo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.mybatis.UsersMybatisRepository;
import project3.yakdo.validation.form.LoginForm;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
	/*
	 * 회원가입
	 *   회원가입 창
	 *   회원가입 할 때 받은 정보 db테이블에 insert
	 *   
	 * 로그인
	 *   로그인 창
	 */
	private final UsersMybatisRepository usersMybatisRepository;
	
	
	
	/**
	 * 회원 로그인
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String userLogin(Model model) {
		model.addAttribute("user", new LoginForm());
		
		return "/users/userLogin";
	}
	
	
	@PostMapping("/login")
	public String userLoginProcess(Model model, LoginForm loginform) {
//		여기서 데이터 처리
		log.info(loginform.getLoginEmail());
		return "/home";
	}
	
	
	
	/**
	 * TEST
	 * userList를 화면에 띄울 메소드
	 * @param model
	 * @return
	 */
	@GetMapping("/userList")
	public String showUser(Model model) {
		List<Users> userList = usersMybatisRepository.selectAll();
		log.info(userList.toString());
		model.addAttribute("userList", userList);
		return "/users/userList";
	}
}
