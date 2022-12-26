package project3.yakdo.domain.BBS;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
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
