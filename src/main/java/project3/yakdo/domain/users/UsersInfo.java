package project3.yakdo.domain.users;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersInfo {
	private Integer userNo;
	private Integer familyNo;
	private Date birth;
	private String gender;
	private String usingDrugs;
	private String allergy;
	private Integer weight;
	
	
}
