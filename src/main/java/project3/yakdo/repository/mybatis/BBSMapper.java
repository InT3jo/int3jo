package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.BBS.BBS;

@Mapper // Mybatis 와 인터페이스 함수 연결
public interface BBSMapper {
	// 게시판 관련
	public Integer insertBBS(BBS bbs); // 게시글 생성 Create

	public BBS selectBybbsNo(int bbsNo); // 게시글 읽기 Read

	public List<BBS> selectByShowZero(); // 게시글 목록 출력

	public void updateBBS(@Param("bbsNo") int bbsNo, @Param("updateItem") BBS bbs); // 게시글 수정 Update

	public void updateShowOneBybbsNo( int bbsNo); // 본인삭제 / 게시글 전체목록에서 숨기기

	public void updateShowTwoBybbsNo( int bbsNo); // 관리자삭제 / 게시글 전체목록에서 숨기기


	// 관리자 관련

	public List<BBS> selectByShowOne(); // 관리자용 본인삭제 게시글 목록 출력

	public List<BBS> selectByShowTwo(); // 관리자용 관리자삭제 게시글 목록 출력
	
	public void updateShowZeroBybbsNo(int bbsNo); // 관리자 삭제 게시글 복구

}
