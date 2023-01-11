//package project3.yakdo.validation;
//
//import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//
//@Component
//public class UsersValidator{
//	/**
//	 * 입력 받은 닉네임이 비어 있을 때
//	 * 닉네임을 입력해 주세요 를 띄워야하고
//	 * 
//	 * 기존 닉네임과 새로 입력받은 닉네임이 같고
//	 * 업데이트가 실패했을 때
//	 * 사용 중인 닉네임입니다 띄우는 validation 만들어야함
//	 * userNick 변경 시, 중복 되는 내용 있는 지 검사
//	 * @param loginForm
//	 * @param errors
//	 * @return 0 : 성공, 1 : 실패
//	 * 담당자 : 빙예은
//	 */
//	public boolean searchUserValidation(Model model, String userEmail) {
//		boolean validateResult = true;
//		if(userEmail == null || userEmail.trim().equals(userEmail)) {
//			validateResult = false;
////			model.addAttribute("userEmailError", "이메일은 필수 입력 값입니다");
//			return validateResult;
//		}
//		return validateResult;
//	}
//}
