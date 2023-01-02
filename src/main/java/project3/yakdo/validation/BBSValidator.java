package project3.yakdo.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import project3.yakdo.domain.BBS.BBS;

public class BBSValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BBS.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		BBS bbs = (BBS)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bbsTitle", "required.bbs.bbsTitle");
		
	
		
	}

}
