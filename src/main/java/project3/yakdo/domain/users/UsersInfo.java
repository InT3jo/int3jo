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
	private List<String> usingDrugs;	//복용중인 약물
	private List<String> allergy;		//알러지
	private Integer weight;		//체중
}
