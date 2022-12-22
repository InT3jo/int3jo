package project3.yakdo.domain.BBS;

import java.sql.Date;

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
	private Date bbsDate;
	private int bbsShow;
	
	//option
	private String userNick;
	
	
	
//	public BBS() {}
//	public BBS(int BBS_no, int user_no, String BBS_title, String BBS_content, Date BBS_date, int BBS_show) {
//		super();
//		this.BBS_no = BBS_no;
//		this.user_no = user_no;
//		this.BBS_title = BBS_title;
//		this.BBS_content = BBS_content;
//		this.BBS_date = BBS_date;
//		this.BBS_show = BBS_show;
//	}
	
//	private BBS() {}
//	public BBS NoArgBBS() {
//		BBS bbs = new BBS() {};
//		return bbs;
//	}
	
//	public BBS(String BBS_title, String BBS_content) {
//		this.BBS_title = BBS_title;
//		this.BBS_content = BBS_content;
//	}
	
	
	
//	
//	
//	
//	public BBS() {}
//	
//	public BBS(int user_no, String BBS_title, String BBS_content,
//			Date BBS_date, int BBS_show) {
//		super();
//		this.user_no = user_no;
//		this.BBS_title = BBS_title;
//		this.BBS_content = BBS_content;
//		this.BBS_date = BBS_date;
//		this.BBS_show = BBS_show;
//	}
}