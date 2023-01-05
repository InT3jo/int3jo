package project3.yakdo.domain.BBS;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reply {
	/* Reply 테이블 컬럼
	re_no,
	bbs_no,
	user_no,
	re_title,
	re_content,
	re_date,
	re_show
	*/
	
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
