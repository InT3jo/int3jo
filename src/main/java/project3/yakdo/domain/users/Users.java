package project3.yakdo.domain.users;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
	private Integer userNo;
	private String userEmail;
	private String userPw;
	private String userNick;
	private Timestamp joinDate;
	private Integer userGrade;
	private Integer userStatus;
	private Timestamp leaveDate;
	private String blockReason;
	
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
	
	
//	
//	public Users(String userEmail, String userPw, String userNick, Timestamp joinDate, Integer userGrade) {
//		super();
//		this.userEmail = userEmail;
//		this.userPw = userPw;
//		this.userNick = userNick;
//		this.joinDate = joinDate;
//		this.userGrade = userGrade;
//	}

	
	
}