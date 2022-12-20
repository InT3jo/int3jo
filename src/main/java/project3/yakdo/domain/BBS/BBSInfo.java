package project3.yakdo.domain.BBS;

import java.sql.Date;
//게시판 목록 화면에 표시되어야하는 요소들 모음

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BBSInfo {
	private int BBS_no;	//게시글 번호
	private String BBS_title;	//게시글 제목
	private String user_nick;	//사용자 닉네임
	private Date BBS_date;		//게시글 작성일
}
