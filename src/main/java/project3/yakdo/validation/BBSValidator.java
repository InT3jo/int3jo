package project3.yakdo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project3.yakdo.domain.BBS.BBS;

public class BBSValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return  BBS.class.isAssignableFrom(clazz);
	}

	/**
	 * 게시글 제목, 내용 공백 입력 체크 
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		BBS bbs = (BBS) target;
		String bbsTitle = bbs.getBbsTitle();
		String bbsContent = bbs.getBbsContent();
		
		if(bbsTitle == null || bbsTitle.trim().isEmpty()) {
			errors.rejectValue("bbsTitle", "null or empty title value");
		
		}
		if(bbsContent == null || bbsContent.trim().isEmpty()) {
			errors.rejectValue("bbsContent", "null or empty content value");
		}		
	}
}
