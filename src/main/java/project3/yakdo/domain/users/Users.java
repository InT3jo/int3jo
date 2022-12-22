package project3.yakdo.domain.users;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
	private Integer userNo;
	private String userEmail;
	private String userPw;
	private String userNick;
	private LocalDateTime joinDate;
	private Integer userGrade;
	private Integer userStatus;
	private LocalDateTime leaveDate;
	private String blockReason;
	
	public Users() {
		
	}
	
//	public Users(String userEmail, String userPw, String userNick, LocalDateTime joinDate
//			, Integer userGrade, Integer userStatus, LocalDateTime leaveDate, String blockReason) {
//		
//	}
}