package project3.yakdo.domain.users;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	private Integer userNo;			//회원번호
	private String userEmail;		//이메일
	private String userPw;			//비밀번호
	private String userNick;		//닉네임
	private Timestamp joinDate;		//가입일
	private Integer userGrade;		//회원등급
	private Integer userStatus;		//활동여부
	private Timestamp leaveDate;	//탈퇴일
	private String blockReason;		//블락사유
	
	public Users(String userEmail, String userPw, String userNick, Timestamp joinDate, Integer userGrade,
			Integer userStatus, Timestamp leaveDate, String blockReason) {
		super();
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.userNick = userNick;
		this.joinDate = joinDate;
		this.userGrade = userGrade;
		this.userStatus = userStatus;
		this.leaveDate = leaveDate;
		this.blockReason = blockReason;
	}
}