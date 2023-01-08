package project3.yakdo.service.users;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.LoginForm;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

	private final UsersRepository usersRepository;
	
	/**
	 * 로그인이 실행되는 메소드
	 * @param loginForm
	 * @return
	 */
	public Users login(LoginForm loginForm) {
		log.info("loginForm{}", loginForm);
		Users user = usersRepository.selectUserByUserEmail(loginForm.getLoginEmail());
		log.info("user {}", user);
		if(user != null) {
			if(user.getUserPw().equals(loginForm.getLoginPw())) {
				log.info("로그인 성공");
				return user;
			}
		}
		return null;
	}
	
	/**
	 * 로그인 된 유저 정보를 찾아서 반환
	 * @param: HttpServletRequest
	 * @return: User
	 */
	public Users getLoginUser(HttpServletRequest req) {
//		HttpSession session = req.getSession(false);
//		if(session == null) {
//			return null;
//		}
//		Users user = (Users)session.getAttribute(SessionVar.LOGIN_MEMBER);
		
		// 개발중 1@1로 로그인한 채로 시작하도록 설계
		Users user = usersRepository.selectUserByUserEmail("1@1");
		return user;
	}

	/**
	 * Users를 받아 UsersInfoList를 반환
	 * @param: Users
	 * @return: List<UsersInfo>
	 */
	public List<UsersInfo> getUsersInfoListByUserNo(Integer userNo) {
		// TODO Auto-generated method stub
		List<UsersInfo> usersInfoList = usersRepository.selectUsersInfoByUsersNo(userNo);
		return usersInfoList;
	}
}
