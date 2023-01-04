package project3.yakdo.domain.users;

import lombok.Data;

@Data
public class SignUpForm {
	private Integer userNo;		//userNo
	private String userEmail;	//이메일
	private String emailCodeCheck;	//이메일 인증 코드
	private String userPw;		//비밀번호
	private String reJoinPw;		//비밀번호 재확인
	private String userNick;	//닉네임

}
