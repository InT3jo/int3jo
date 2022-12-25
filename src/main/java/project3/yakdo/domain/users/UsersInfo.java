package project3.yakdo.domain.users;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersInfo {
	private Integer userNo;
	private Integer familyNo;
	private LocalDateTime birth;
	private String gender;
	private String usingDrugs;
	private String allergy;
	private String weight;
	
	public UsersInfo() {
		
	}
	
	
}
