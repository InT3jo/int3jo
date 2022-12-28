/**
 * 유저 페이지 컨트롤러
 * 담당: 빙예은
 */
package project3.yakdo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.repository.mybatis.UsersMybatisRepository;

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
	 * 
	 * 담당자 : 빙예은
	 */
	@GetMapping("/userList")
	public String showUser(Model model) {
//		List<Users> userList = usersMybatisRepository.selectAllUsers();
//		log.info(userList.toString());
//		model.addAttribute("userList", userList);
		return "/users/login/userList";
	}
//
//	/**
//	 * TEST
//	 * session을 이용해 유효성 검사 후
//	 * 로그인 하지 않았으면 return 이전 페이지
//	 * 로그인 한 상태라면 /users/login/userList
//	 * 
//	 * @param model
//	 * @return
//	 * 
//	 * 담당자 : 빙예은
//	 */
//	@GetMapping
//	public String Users(Model model, HttpServletRequest req) {
//		
//		HttpSession session = req.getSession();
//		
//		if(session == null || session.getAttribute(SessionVar.LOGIN_MEMBER) == null) {
//			return "redirect:/";
//		}
//		
//		return "/users/login/userList";
//	}
	
}
