package project3.yakdo.validation.form;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * form 태그 위한 service 객체
 * @author honey
 *
 */
@Data
public class JoinForm {
	private String userEmail;
	private String userPw;
	private String userNick;
	private String joinDate;
	private Integer userGrade;
	
	private Integer familyNo;
	private String birth;
	private String gender;
	private String usingDrugs;
	private String allergy;
	private String weight;
}
