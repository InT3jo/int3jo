package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.BBS.BBSComment;

@Mapper
public interface BBSCommentMapper {
	// 댓글 쓰기
	public Integer insertBBSCom(BBSComment BBSComment);
	
	// 글번호에 해당하는 댓글 리스트 출력(게시상태만)
	public List<BBSComment> selectComBybbsNo(Integer bbsNo);
	
	//댓글 수정
	public void updateCom(@Param("bbsNo") int bbsNo,@Param("comNo") int comNo, @Param("updateCom") BBSComment bbsComment);
	
	//댓글 본인 삭제
	public void updateComShowOneBbn(@Param("bbsNo") Integer bbsNo, @Param("comNo") Integer comNo);
	
	//댓글 관리자 삭제 
	public void updateComShowTwoBybbsNo(@Param("bbsNo") Integer bbsNo
											,@Param("comNo")int comNo);
	//댓글 특정
	public BBSComment selectOneCom(@Param("bbsNo") int bbsNo, @Param("comNo") int comNo);
}



