package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.BBS.BBS;

public interface BBSRepository {

	public BBS insert(BBS BBS);	//게시글 생성 Create
	
	public BBS selectByBBS_no(int BBS_no); //게시글 읽기 Read
	
	public List<BBS> selectByBBS_show_0();	// 게시글 목록 출력 Read
	
	public boolean update(int BBS_no, BBS BBS);	// 게시글 수정 Update
	
	public boolean updateBBS_show_1_ByBBS_no(int BBS_no);	// 본인삭제 / 게시글 전체목록에서 숨기기
	
	public boolean updateBBS_show_2_ByBBS_no(int BBS_no);	// 관리자삭제 / 게시글 전체목록에서 숨기기
	
	
}
