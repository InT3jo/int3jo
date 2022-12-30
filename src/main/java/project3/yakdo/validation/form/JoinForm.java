package project3.yakdo.validation.form;

import java.util.List;

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
	private String emailCodeCheck;	//이메일 인증 코드
	private String userPw;		//비밀번호
	private String reJoinPw;		//비밀번호 재확인
	private String userNick;	//닉네임
	private String joinDate;	//가입일
	private Integer userGrade;	//회원등급

//	private Integer familyNo;	//가족번호
//	private String birth;		//생년월일
//	private String gender;		//성별
//	
//	//usingDrugs, allergy를 여기서 List로 받아야함
//	private List<String> usingDrugs;	//복용 중인 약
//	private List<String> allergy;		//알러지
//	
//	private Integer weight;		//체중
}
