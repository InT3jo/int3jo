package project3.yakdo.domain.users;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersInfo {
	private int user_no;
	private int family_no;
	private LocalDateTime birth;
	private String gender;
	private String using_drugs;
	private String allergy;
	private String weight;
	private String block_reason;
}
