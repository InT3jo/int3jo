package project3.yakdo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.BBSComment;

public class BBSComValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return  BBSComment.class.isAssignableFrom(clazz);
	}

	/**
	 * 댓글 공백 입력 체크 
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		BBSComment bbsCom = (BBSComment) target;
		String comContent = bbsCom.getComContent();
		
		if(comContent == null || comContent.trim().isEmpty()) {
			errors.rejectValue("comContent", "null or empty content value");
		}		
	}

}
