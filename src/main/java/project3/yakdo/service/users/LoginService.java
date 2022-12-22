package project3.yakdo.service.users;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.LoginForm;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final UsersRepository usersRepository;
	
	public Users login(LoginForm loginForm) {

		Users user = usersRepository.selectByUserEmail(loginForm.getLoginEmail());
		
		if(user != null) {
			if(user.getUserPw().equals(loginForm.getLoginPw())) {
				return user;
			}
		}
		return null;
	}
}
