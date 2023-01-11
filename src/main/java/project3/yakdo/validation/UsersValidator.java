package project3.yakdo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class UsersValidator{
	/**
	 * 비밀번호 변경에 관련된 validator
	 * 
	 * @param model
	 * @param userEmail
	 * @param newPw
	 * @param newPwConfirm
	 * @return true : 성공, false : 실패
	 * 
	 * 담당자 : 빙예은
	 */
	public boolean passwordValidate(Model model, String userEmail, String newPw, String newPwConfirm) {
		// 공백 불가
		if((newPw.isEmpty() || newPw.trim().isBlank())
				&& (newPwConfirm.isEmpty() || newPwConfirm.trim().isBlank())) {
			model.addAttribute("errorMsg", "비밀번호를 입력해 주세요");
			return false;
		}
		// 앞뒤 공백 여부 확인
		if(newPw.equals(newPw.trim()) == false) {
			model.addAttribute("errorMsg", "비밀번호에 공백은 들어갈 수 없습니다");
			return false;
		}
		// 새 비밀번호, 새 비밀번호 같은지 비교
		if(!newPw.equals(newPwConfirm)) {
			model.addAttribute("errorMsg", "비밀번호가 일치하지 않습니다");
			return false;
		}
		// 비밀번호 포맷 확인 (이메일과 같은지 확인)
		if(newPw.equals(userEmail)) {
			model.addAttribute("errorMsg", "비밀번호와 이메일은 같을 수 없습니다");
			return false;
		}
		// 비밀번호 포맷 확인 (영어 대소문자, 숫자 포함 8자 이상)
		Pattern pwFormat = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$");
		Matcher psFmMatcher = pwFormat.matcher(newPw);
		if(psFmMatcher.find() == false) {
			model.addAttribute("errorMsg", "안전하지 않은 비밀번호입니다");
			return false;
		}
		// 비밀번호 포맷 확인 (특수문자 확인 <,>)
		if(newPw.contains("<") || newPw.contains(">")) {
			model.addAttribute("errorMsg", "사용할 수 없는 비밀번호입니다");
			return false;
		}
		return true;
	}
}
