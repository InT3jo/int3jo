package project3.yakdo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project3.yakdo.domain.BBS.Reply;

public class ReplyValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return  Reply.class.isAssignableFrom(clazz);
	}

	/**
	 * 답변 내용 공백 입력 체크 
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Reply bbsCom = (Reply) target;
		String reContent = bbsCom.getReContent();
		
		if(reContent == null || reContent.trim().isEmpty()) {
			errors.rejectValue("reContent", "null or empty content value");
		}		

	}
}
