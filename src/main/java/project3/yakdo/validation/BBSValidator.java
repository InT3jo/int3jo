package project3.yakdo.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import project3.yakdo.validation.form.WriteBBSForm;

public class BBSValidator {

	public void validateWriteBBSFrom(WriteBBSForm writeBBSform, Errors errors) {
		if(!StringUtils.hasText(writeBBSform.getBbsTitle())) {
			errors.rejectValue("bbsTitle", null, "제목을 입력해주세요.");
		}
		if(!StringUtils.hasText(writeBBSform.getBbsContent())) {
			errors.rejectValue("bbsContent", null, "내용을 입력해주세요");
		}
	}

}
