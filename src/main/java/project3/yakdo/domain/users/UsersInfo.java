package project3.yakdo.domain.users;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersInfo {
	private Integer userNo;		//회원번호
	private Integer familyNo;	//가족번호
	private Date birth;			//생년월일
	private String gender;		//성별
	private Integer weight;		//체중
	
	private List<String> usingDrugList;	//복용중인 약물
	private List<String> allergyList;		//알러지
	
	public UsersInfo() {
		
	}
}
