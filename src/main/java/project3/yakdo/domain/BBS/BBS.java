package project3.yakdo.domain.BBS;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BBS {
	//default 
	private int bbsNo;
	private int userNo;
	private String bbsTitle; 
	private String bbsContent;
	private Timestamp bbsDate;
	private int bbsShow;
	
	//option
	private String userNick;
	
	

}