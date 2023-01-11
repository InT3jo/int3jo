package project3.yakdo.domain.BBS;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reply {
	
	//default 
		private int reNo;
		private int bbsNo;
		private int userNo;
		private String reTitle; 
		private String reContent;
		private Timestamp reDate;
		private int reShow;
		
		//option
		private String userNick;
}
