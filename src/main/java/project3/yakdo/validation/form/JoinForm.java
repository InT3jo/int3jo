package project3.yakdo.validation.form;

import lombok.Data;

/**
 * form 태그 위한 service 객체
 * @author honey
 *
 */
@Data
public class JoinForm {
	private String userEmail;	//이메일
	private String userPw;		//비밀번호
	private String userNick;	//닉네임
	private String joinDate;	//가입일
	private Integer userGrade;	//회원등급
	
	private Integer familyNo;	//가족번호
	private String birth;		//생년월일
	private String gender;		//성별
	private String usingDrugs;	//복용 중인 약
	private String allergy;		//알러지
	private Integer weight;		//체중
	
	private String emailCodeCheck;	//이메일 인증 코드
	private String ReJoinPw;		//비밀번호 재확인
}
