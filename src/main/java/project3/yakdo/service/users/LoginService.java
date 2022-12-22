package project3.yakdo.service.users;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.LoginForm;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

	private final UsersRepository usersRepository;
	
	public Users login(LoginForm loginForm) {
		log.info("loginForm{}", loginForm);
		Users user = usersRepository.selectByUserEmail(loginForm.getLoginEmail());
		log.info("user {}", user);
		if(user != null) {
			if(user.getUserPw().equals(loginForm.getLoginPw())) {
				log.info("로그인 성공");
				return user;
			}
		}
		return null;
	}
}
