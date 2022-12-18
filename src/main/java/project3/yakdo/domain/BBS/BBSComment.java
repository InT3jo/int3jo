package project3.yakdo.domain.BBS;

import java.util.Date;

import lombok.Data;
@Data
public class BBSComment {
	private int BBS_no;
	private int com_no;
	private int user_no;
	private String com_content;
	private Date com_date;
	private int com_show;
}
