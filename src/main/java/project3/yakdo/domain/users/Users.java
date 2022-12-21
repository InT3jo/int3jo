package project3.yakdo.domain.users;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Users {
	private int user_no;
	private String user_email;
	private String user_pw;
	private String user_nick;
	private LocalDateTime join_date;
	private int user_status;
	private LocalDateTime leave_date;
	private String block_reason;
}
