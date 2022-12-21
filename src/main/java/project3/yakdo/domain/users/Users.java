package project3.yakdo.domain.users;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
	private int userNo;
	private String userEmail;
	private String userPw;
	private String userNick;
	private LocalDateTime joinDate;
	private int userGrade;
	private int userStatus;
	private LocalDateTime leaveDate;
	private String blockReason;
}