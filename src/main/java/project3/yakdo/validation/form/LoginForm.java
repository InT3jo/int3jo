package project3.yakdo.validation.form;

import lombok.Data;
import project3.yakdo.domain.users.Users;

@Data
public class LoginForm {
	private String loginEmail;
	private String loginPw;
	private Users user;
	
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail.trim();
	}
}
