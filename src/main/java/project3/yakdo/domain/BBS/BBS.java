package project3.yakdo.domain.BBS;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class BBS {
	private int BBS_no;
	private int user_no;
	private String BBS_title; 
	private String BBS_content;
	private Date BBS_date;
	private int BBS_show;
	
//	private BBS() {}
//	public BBS NoArgBBS() {
//		BBS bbs = new BBS() {};
//		return bbs;
//	}
	
	
//	public BBS(int BBS_no, int user_no, String BBS_title, String BBS_content, Date BBS_date, int BBS_show) {
//		super();
//		this.BBS_no = BBS_no;
//		this.user_no = user_no;
//		this.BBS_title = BBS_title;
//		this.BBS_content = BBS_content;
//		this.BBS_date = BBS_date;
//		this.BBS_show = BBS_show;
//	}
//	public BBS() {}
	
//	
//	
//	
//	public BBS() {}
//	
//	public BBS(int user_no, String BBS_title, String BBS_content,
//			Date BBS_date, int BBS_show) {
//		super();
//		this.BBS_no = user_no;
//		this.BBS_title = BBS_title;
//		this.BBS_content = BBS_content;
//		this.BBS_date = BBS_date;
//		this.BBS_show = BBS_show;
//	}
}