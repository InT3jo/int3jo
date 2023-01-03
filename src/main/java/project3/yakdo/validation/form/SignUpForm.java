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
//	private Integer familyNo;	//가족번호
//	private String familyNick;	//familyNick
//	private String birth;		//생년월일
//	private String gender;		//성별
//	private double weight;		//체중
//	private List<String> usingDrugList;	//복용 중인 약
//	private List<String> allergyList;		//알러지
	
	private List<UsersInfo> familyList;
}
