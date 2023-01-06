package project3.yakdo.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import project3.yakdo.validation.form.LoginForm;

public class UsersValidator {
	/**
	 * userNick 변경 시, 중복 되는 내용 있는 지 검사
	 * @param loginForm
	 * @param errors
	 * 
	 * 담당자 : 빙예은
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
