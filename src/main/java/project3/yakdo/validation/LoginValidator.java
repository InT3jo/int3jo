package project3.yakdo.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import project3.yakdo.validation.form.LoginForm;


@Component
public class LoginValidator implements Validator {

	/**
	 * login시 이메일, 패스워드 검증할 메소드
	 * @param loginForm
	 * @param errors
	 * 
	 * 담당자 : 빙예은
	 */
//	public void validateLoginForm(LoginForm loginForm, Errors errors) {
//		if(!StringUtils.hasText(loginForm.getLoginEmail())) {
//			errors.rejectValue("loginEmail", null, "이메일을 입력해주세요.");
//		}
//		if(!StringUtils.hasText(loginForm.getLoginPw())) {
//			errors.rejectValue("loginPw", null, "비밀번호를 입력해주세요");
//		}
//	}

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginPw", "비밀번호를 입력해 주세요");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginEmail", "이메일을 입력해 주세요");
		LoginForm loginForm = (LoginForm)target;
		if(loginForm.getLoginEmail() == null || loginForm.getLoginEmail().trim().equals("")) {
//			errors.rejectValue("loginEmail", "emailError", "이메일을 입력해 주세요", null);
//			errors.rejectValue("loginPw", "pwError", "비밀번호를 입력해 주세요", null);
			errors.rejectValue(null, null, null, null);
		}
		if(loginForm.getLoginPw() == null || loginForm.getLoginPw().trim().equals("")) {
		}
//		if(!loginForm.getLoginEmail().contains("@")) {
//			errors.reject("loginEmail", "이메일 주소에 \'@\'를 추가해 주세요");
//		}
	}
	
}
