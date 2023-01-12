package project3.yakdo.service.users;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.session.SessionVar;
import project3.yakdo.validation.LoginValidator;
import project3.yakdo.validation.form.LoginForm;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

	private final UsersRepository usersRepository;
	private final LoginValidator loginValidator;
	
	/**
	 * 로그인이 실행되는 메소드
	 * @param loginForm
	 * @return
	 * 담당자 : 빙예은
	 */
	public Users login(Model model, LoginForm loginForm, BindingResult bindingResult) {
		if(loginValidate(model, loginForm, bindingResult) == 1) {
			Users user = usersRepository.selectUserByUserEmail(loginForm.getLoginEmail());
			loginForm.setUser(user);
			model.addAttribute("userError", bindingResult.getFieldError("user").getCode());
			if(user != null) {
				if(user.getUserPw().equals(loginForm.getLoginPw())) {
					log.info("로그인 성공");
					return user;
				}
			}
		}
		return null;
	}
	
	/**
	 * 로그인 된 유저 정보를 찾아서 반환
	 * @param: HttpServletRequest
	 * @return: User
	 * 
	 * 담당자 : 빙예은
	 */
	public Users getLoginUser(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			return null;
		}
		Users user = (Users)session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		return user;
	}

	/**
	 * Users를 받아 UsersInfoList를 반환
	 * @param: Users
	 * @return: List<UsersInfo>
	 * 
	 * 담당자 : 빙예은
	 */
	public List<UsersInfo> getUsersInfoListByUserNo(Integer userNo) {
		// TODO Auto-generated method stub
		List<UsersInfo> usersInfoList = usersRepository.selectUsersInfoByUsersNo(userNo);
		return usersInfoList;
	}
	

	private Integer loginValidate(Model model, LoginForm loginForm, BindingResult bindingResult) {
		loginValidator.validate(loginForm, bindingResult);
		Integer validateValue = 1;
		if(bindingResult.hasFieldErrors("loginEmail")) {
			validateValue = 0;
			model.addAttribute("emailError", bindingResult.getFieldError("loginEmail").getCode());
			return validateValue;
		}
		
		if(bindingResult.hasFieldErrors("loginPw"))	{
			validateValue = 0;
			model.addAttribute("passwordError", bindingResult.getFieldError("loginPw").getCode());
			return validateValue;
		}
		return validateValue;
	}
	
	/**
	 * 로그아웃이 실행되는 메소드
	 * @param req
	 * 
	 * 담당자 : 빙예은
	 */
	public void logoutService(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		
		if (session != null && session.getAttribute(SessionVar.LOGIN_MEMBER) != null){
			session.removeAttribute(SessionVar.LOGIN_MEMBER);
		}
	}
}
