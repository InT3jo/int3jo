package project3.yakdo.validation.form;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * join.html form 태그를 담기 위한 객체 클래스
 * userGrade : 회원등급
 * 	0 : 운영진
 * 	1 : 관리자
 * 	2 : 회원
 * 
 * 
 * @author honey
 *
 * 담당자 : 빙예은
 */
@Data
@NoArgsConstructor
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
	private String reJoinPw;		//비밀번호 재확인
	
	
	/**
	 * 회원가입 시 필요한 이메일 인증 코드와 비밀번호 재확인을 담는 생성자
	 * @param emailCodeCheck
	 * @param reJoinPw
	 * 
	 * 담당자 : 빙예은
	 */
	JoinForm(String emailCodeCheck, String reJoinPw) {
		this.emailCodeCheck = emailCodeCheck;
		this.reJoinPw = reJoinPw;
	}
}
