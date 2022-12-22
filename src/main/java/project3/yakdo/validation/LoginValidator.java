package project3.yakdo.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import project3.yakdo.validation.form.LoginForm;

public class LoginValidator {

	/**
	 * login시 이메일, 패스워드 검증할 메소드
	 * @param loginForm
	 * @param errors
	 */
	public void validateLoginForm(LoginForm loginForm, Errors errors) {
		if(!StringUtils.hasText(loginForm.getLoginEmail())) {
			errors.rejectValue("loginEmail", null, "이메일을 입력해주세요.");
		}
		if(!StringUtils.hasText(loginForm.getLoginPw())) {
			errors.rejectValue("loginPw", null, "비밀번호를 입력해주세요");
		}
	}
	
}
