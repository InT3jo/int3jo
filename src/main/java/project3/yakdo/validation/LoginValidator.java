package project3.yakdo.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import project3.yakdo.validation.form.LoginForm;

@Slf4j
@Component
public class LoginValidator implements Validator {

	/**
	 * login시 이메일, 패스워드 검증할 메소드
	 * @param loginForm
	 * @param errors
	 * 
	 * 담당자 : 빙예은
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginEmail", "이메일을 입력해 주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginPw", "비밀번호를 입력해 주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "아이디 또는 비밀번호를 다시 확인해 주세요");
		LoginForm loginForm = (LoginForm) target;
		log.info("들어옴??????? {}", loginForm.getUser());
//		if(loginForm.getUser() != null) {
//			if((!loginForm.getLoginEmail().equals(loginForm.getUser().getUserEmail()))
//					|| (!loginForm.getLoginPw().equals(loginForm.getUser().getUserPw()))) {
//				errors.rejectValue("user", "아이디 또는 비밀번호를 다시 확인해 주세요");
//			}
//		}
	}
	
}
