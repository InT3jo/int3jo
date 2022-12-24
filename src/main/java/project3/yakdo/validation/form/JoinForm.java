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
	private Integer userNo;
	private String userEmail;
	private String userPw;
	private String userNick;
	private LocalDateTime joinDate;
	private Integer userGrade;
	private Integer userStatus;
	private LocalDateTime leaveDate;
	private String blockReason;
	
	private Integer familyNo;
	private LocalDateTime birth;
	private String gender;
	private String usingDrugs;
	private String allergy;
	private String weight;
}
