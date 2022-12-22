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
	private int userGrade;
	private int userStatus;
	private LocalDateTime leaveDate;
	private String blockReason;
	
	public Users() {
		
	}
	
	public Users(String userEmail, String userPw, String userNick, LocalDateTime joinDate
			, int userGrade, int userStatus, LocalDateTime leaveDate, String blockReason) {
		
	}
}