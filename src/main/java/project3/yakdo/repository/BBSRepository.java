package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.Criteria;
import project3.yakdo.domain.BBS.Reply;
import project3.yakdo.domain.BBS.SearchCriteria;

public interface BBSRepository {

	
/* 게시판 관련 */
	
	public BBS insertBBS(BBS bbs); // 게시글 생성 Create

	public BBS selectBybbsNo(int bbsNo); // 게시글 읽기 Read

	public List<BBS> selectByShowZero(); // 게시글 목록 출력

	public boolean updateBBS(int bbsNo, BBS bbs); // 게시글 수정 Update

	public boolean updateShowOneBybbsNo(int bbsNo); // 본인삭제 / 게시글 전체목록에서 숨기기
	
    
	public List<BBS> listSearch(SearchCriteria scri); 	//목록 + 페이징 + 검색 
		
	
	public int countSearch(SearchCriteria scri); //검색 결과 갯수

/*관리자 관련 */
	
	public boolean updateShowTwoBybbsNo(int bbsNo); // 관리자삭제 / 게시글 전체목록에서 숨기기

	public List<BBS> selectByShowOne(); // 관리자용 본인삭제 게시글 목록 출력

	public List<BBS> selectByShowTwo(); // 관리자용 관리자삭제 게시글 목록 출력

	public boolean updateShowZeroBybbsNo(int bbsNo); // 관리자 삭제 게시글 복구

	//본인 삭제 글 리스트 + 페이징 + 검색 
	public List<BBS> adminShowOnelist(SearchCriteria scri);
	
	//본인삭제 검색 결과 갯수 
	public int countSearchShowOne(SearchCriteria scri);
	
	//관리자 삭제 글 리스트 + 페이징 + 검색 
	public List<BBS> adminShowTwolist(SearchCriteria scri);
		
	//관리자 삭제 검색 결과 갯수 
	public int countSearchShowTwo(SearchCriteria scri);
	
/* 답변 관련 */ 
	
	//답변 쓰기 
	public Reply insertReply(Reply reply);
	
	//글번호 bbs_no에 해당하는 답변 불러오기
	public List<Reply> listReBybbsNo(int bbsNo);
	
	//전체 답변 불러오기
	public List<Reply> listRe();
	
	//답변 상세보기 
	public Reply replyView2(int bbsNo, int reNo);
	
	//답변 수정 
	public boolean updateRe2(int bbsNo,int reNo, Reply reply); 
	
	//답변 삭제 
	public boolean updateReShow1(int bbsNo,int reNo);
	
	
	
	
	
	
	
	
	
	
	
	
}
