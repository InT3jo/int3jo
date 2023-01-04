package project3.yakdo.validation.form;

import java.util.List;

import lombok.Data;
import project3.yakdo.domain.users.UsersInfo;

@Data
public class SignUpForm {
	private Integer userNo;		//userNo
	private String userEmail;	//이메일
	private String emailCodeCheck;	//이메일 인증 코드
	private String userPw;		//비밀번호
	private String reJoinPw;		//비밀번호 재확인
	private String userNick;	//닉네임
	private List<UsersInfo> familyList;	//가족리스트(가족명칭, 생년월일, 성별, 체중, 복용중인 약 리스트, 알러지 리스트)
	
	/*아직 지우지 말아주세요
	 * 
	 * 
//	private Integer familyNo;	//가족번호
//	private String familyNick;	//familyNick
//	private String birth;		//생년월일
//	private String gender;		//성별
//	private String weight;		//체중
//	private List<String> usingDrugList;	//복용 중인 약
//	private List<String> allergyList;		//알러지
	 * 
	 */
}
