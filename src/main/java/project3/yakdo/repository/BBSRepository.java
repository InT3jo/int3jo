package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.BBSInfo;

public interface BBSRepository {

	public BBS insertBBS(BBS bbs); // 게시글 생성 Create

	public BBS selectBybbsNo(int bbsNo); // 게시글 읽기 Read

	public List<BBS> selectByShowZero(); // 게시글 목록 출력
	
	public boolean updateBBS(int bbsNo, BBS bbs); // 게시글 수정 Update

	public boolean updateShowOneBybbsNo(int bbsNo); // 본인삭제 / 게시글 전체목록에서 숨기기
						
	public boolean updateShowTwoBybbsNo(int bbsNo); // 관리자삭제 / 게시글 전체목록에서 숨기기

	
	//======================================================
	//관리자 관련
	
	public List<BBS> selectByShowOne();	//관리자용 본인삭제 게시글 목록 출력
	
	public List<BBS> selectByShowTwo();	//관리자용 관리자삭제 게시글 목록 출력
	
}
