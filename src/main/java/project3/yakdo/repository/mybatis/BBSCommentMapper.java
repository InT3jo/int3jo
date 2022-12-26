package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project3.yakdo.domain.BBS.BBSComment;

@Mapper
public interface BBSCommentMapper {
	// 댓글 쓰기
	public Integer insertBBSCom(BBSComment BBSComment);
	
	// 글번호에 해당하는 댓글 리스트 출력(게시상태만)
	public List<BBSComment> selectComBybbsNo(Integer bbsNo);
	
	//댓글 수정
	public void updateCom(int bbsNo, BBSComment bbsComment);
	
	//댓글 본인 삭제
	public void updateComShowOneBybbsNo(int bbsNo);
	
	//댓글 관리자 삭제 
	public void updateComShowTwoBybbsNo(int bbsNo);
	
}
