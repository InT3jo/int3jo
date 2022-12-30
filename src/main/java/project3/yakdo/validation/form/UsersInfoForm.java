package project3.yakdo.validation.form;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 가족 건강 정보를 받기 위한 form
 * @author honey
 *
 */
@Data
@NoArgsConstructor
public class UsersInfoForm {
	private Integer familyNo;	//가족번호
	private String birth;		//생년월일
	private String gender;		//성별
	private Integer weight;		//체중

	//usingDrugs, allergy를 여기서 List로 받아야함
	private List<String> usingDrugs;	//복용 중인 약
	private List<String> allergy;		//알러지
	
}
