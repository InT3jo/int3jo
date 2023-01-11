package project3.yakdo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import project3.yakdo.domain.users.Users;

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
			model.addAttribute("errorMsg", "비밀번호 앞뒤로 공백이 들어갈 수 없습니다");
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
	
	/**
	 * 닉네임 변경에 관련된 validator
	 * @param model
	 * @param userNick
	 * @param user
	 * @return
	 */
	public boolean modifyNickValidate(Model model, String userNick, Users user) {
		if(userNick.isEmpty() && userNick.isBlank()) {
			model.addAttribute("errorMsg", "닉네임을 입력해 주세요");
			return false;
		}
		if(userNick.equals(userNick.trim()) == false) {
			model.addAttribute("errorMsg", "닉네임 앞뒤로 공백이 들어갈 수 없습니다");
			return false;
		}
		if(userNick.equals(user.getUserNick())) {
			model.addAttribute("errorMsg", "현재 사용 중인 이메일 입니다");
			return false;
		}
		return true;
	}
	
	public boolean isSameNick(Model model, String userNick, String sameUserNick) {
		if(userNick.equals(sameUserNick)) {
			model.addAttribute("errorMsg", "다른 회원과 중복되는 닉네임입니다");
			return false;
		}
		return true;
	}

}
