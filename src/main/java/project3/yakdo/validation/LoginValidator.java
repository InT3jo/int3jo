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
		LoginForm loginForm = (LoginForm) target;
		/**
		 * 이메일 관련 validation
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginEmail", "이메일을 입력해 주세요");

		String email = loginForm.getLoginEmail();
		int idx = email.length()-1;
		if(idx < 0) {
			idx=0;
		}
		if(!email.contains("@") || !email.contains(".")
				|| (email.substring(idx).equals("."))) {
			errors.rejectValue("loginEmail", "이메일 형식을 확인해 주세요");
		}
		
		/**
		 * 비밀번호 관련 validation
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginPw", "비밀번호를 입력해 주세요");
		
		/**
		 * user 정보 관련
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "아이디 또는 비밀번호를 다시 확인해 주세요");
		
		

		
	}
	
}
