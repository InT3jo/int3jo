package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.Criteria;
import project3.yakdo.domain.BBS.Reply;
import project3.yakdo.domain.BBS.SearchCriteria;

@Mapper // Mybatis 와 인터페이스 함수 연결
public interface BBSMapper {
	// 게시판 관련
	public Integer insertBBS(BBS bbs); // 게시글 생성 Create

	public BBS selectBybbsNo(int bbsNo); // 게시글 읽기 Read

	public List<BBS> selectByShowZero(); // 게시글 목록 출력

	public void updateBBS(@Param("bbsNo") int bbsNo, @Param("updateItem") BBS bbs); // 게시글 수정 Update

	public void updateShowOneBybbsNo(int bbsNo); // 본인삭제 / 게시글 전체목록에서 숨기기

	public void updateShowTwoBybbsNo(int bbsNo); // 관리자삭제 / 게시글 전체목록에서 숨기기

	// 관리자 관련

	public List<BBS> selectByShowOne(); // 관리자용 본인삭제 게시글 목록 출력

	public List<BBS> selectByShowTwo(); // 관리자용 관리자삭제 게시글 목록 출력

	public void updateShowZeroBybbsNo(int bbsNo); // 관리자 삭제 게시글 복구
	
	
	//목록 + 페이징 + 검색 1번째 방법에 필요
		public List<BBS> listSearch(SearchCriteria scri);
		
		//검색 결과 갯수
		public int countSearch(SearchCriteria scri);
		
		
		//본인 삭제 글 리스트 + 페이징 + 검색 
		public List<BBS> adminShowOnelist(SearchCriteria scri);
		
		//검색 결과 갯수 
		public int countSearchShowOne(SearchCriteria scri);
		
		
		//관리자 삭제 글 리스트 + 페이징 + 검색 
		public List<BBS> adminShowTwolist(SearchCriteria scri);
				
		//관리자 삭제 검색 결과 갯수 
		public int countSearchShowTwo(SearchCriteria scri);
		
		//답글 쓰기 
		public Integer insertReply(Reply reply);
		
		//글번호 bbs_no에 해당하는 답글 불러오기
		public List<Reply> listReBybbsNo(int bbsNo);
		
		//전체 답글 불러오기
		public List<Reply> listRe();
		
		
		//답변 상세보기 2 (테스트 중 되면 이걸로 변경할 예정 01-09-18:54)
		public Reply replyView2(@Param("bbsNo") int bbsNo, @Param("reNo") int reNo);
		
		//답변 수정 2
		public boolean updateRe2(@Param("bbsNo") int bbsNo, @Param("reNo") int reNo, @Param("replyItem") Reply reply);
		
		//답변 삭제 
		public boolean updateReShow1(@Param("bbsNo") int bbsNo, @Param("reNo") int reNo);
}
