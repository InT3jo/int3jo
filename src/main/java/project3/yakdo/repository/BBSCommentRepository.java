package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.BBS.BBSComment;

public interface BBSCommentRepository {

	// 댓글 쓰기
	public BBSComment insertBBSCom(BBSComment BBSComment);
	
	// 글번호에 해당하는 댓글 리스트 출력(게시상태만)
	public List<BBSComment> selectComBybbsNo(Integer bbsNo);
	
	//댓글 수정
	public boolean updateCom(int bbsNo, BBSComment bbsComment);
	
	//댓글 본인 삭제
	public boolean updateComShowOneByBbsNo(Integer bbsNo, Integer comNo);
	
	//댓글 관리자 삭제 
	public boolean updateComShowTwoBybbsNo(Integer bbsNo, int comNo);
	
	
	
	
	
}
