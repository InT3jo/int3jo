package project3.yakdo.domain.BBS;

import java.util.Date;

import lombok.Data;
@Data
public class BBS {
	private int BBS_no;
	private int user_no;
	private String BBS_title;
	private String BBS_content;
	private Date BBS_date;
	private int BBS_show;
}
