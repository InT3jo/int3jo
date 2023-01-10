package project3.yakdo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.BBSComment;

public class BBSComValidator implements Validator{
	//Validator 인터페이스를 가져오면 supports() 메소드와 validate() 메소드를 override

	//supports() 메소드는 검증할 수 있는 오브젝트 타입인지의 여부를 반환
	//supports() 메소드가 true를 반환해야 validate() 메소드가 호출
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return  BBSComment.class.isAssignableFrom(clazz);
	}

//	validate() 메소드는 프로퍼티 바인딩이 완료된 오브젝트에 대해서 검증을 진행하는 메소드
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		BBSComment bbsCom = (BBSComment) target;
		String comContent = bbsCom.getComContent();
		
		if(comContent == null || comContent.trim().isEmpty()) {
			errors.rejectValue("comContent", "null or empty content value");
		}		
		
		
		/*
		if(!StringUtils.hasText(bbs.getBbsTitle())) {
			errors.rejectValue("bbsTitle", null, "제목을 입력해주세요.");
		}
		if(!StringUtils.hasText(bbs.getBbsContent())) {
			errors.rejectValue("bbsContent", null, "내용을 입력해주세요.");
		}
		*/
	}
	

//	다시 수업 방법으로 해보려고 주석 처리 01-06 10:54
//	public void validateWriteBBSFrom(WriteBBSForm writeBBSform, Errors errors) {
//		if(!StringUtils.hasText(writeBBSform.getBbsTitle())) {
//			errors.rejectValue("bbsTitle", null, "제목을 입력해주세요.");
//		}
//		if(!StringUtils.hasText(writeBBSform.getBbsContent())) {
//			errors.rejectValue("bbsContent", null, "내용을 입력해주세요");
//		}
//	}

}
