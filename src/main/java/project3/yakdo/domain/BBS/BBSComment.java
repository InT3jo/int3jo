package project3.yakdo.domain.BBS;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class BBSComment {
	
	//default
	private int bbsNo;
	private int comNo;
	private int userNo;
	private String comContent;
	private Timestamp comDate;
	private int comShow;
	
	//option
	private String userNick;
}
