package project3.yakdo.validation.form;

import lombok.Data;

@Data
public class UserForm {
	private String userEmail;
	private String code;
	private String userPw;
	private String userPwNew;
	private String userPwNewCheck;
}
