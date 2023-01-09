package project3.yakdo.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project3.yakdo.validation.form.LoginForm;

public class UsersValidator implements Validator{
	/**
	 * 입력 받은 닉네임이 비어 있을 때
	 * 닉네임을 입력해 주세요 를 띄워야하고
	 * 
	 * 기존 닉네임과 새로 입력받은 닉네임이 같고
	 * 업데이트가 실패했을 때
	 * 사용 중인 닉네임입니다 띄우는 validation 만들어야함
	 * userNick 변경 시, 중복 되는 내용 있는 지 검사
	 * @param loginForm
	 * @param errors
	 * 
	 * 담당자 : 빙예은
	 */

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
}
