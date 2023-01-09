package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.Criteria;
import project3.yakdo.domain.BBS.Reply;
import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.validation.form.WriteBBSForm;

public interface BBSRepository {

	public BBS insertBBS(BBS bbs); // 게시글 생성 Create
	
	//유효성 걸면서 form으로 게시글 작성 시도중 01-05 20:59
	public Integer insertBBS(WriteBBSForm writeBBSform);	

	public BBS selectBybbsNo(int bbsNo); // 게시글 읽기 Read

	public List<BBS> selectByShowZero(); // 게시글 목록 출력

	public boolean updateBBS(int bbsNo, BBS bbs); // 게시글 수정 Update

	public boolean updateShowOneBybbsNo(int bbsNo); // 본인삭제 / 게시글 전체목록에서 숨기기

	// 관리자 관련
	public boolean updateShowTwoBybbsNo(int bbsNo); // 관리자삭제 / 게시글 전체목록에서 숨기기

	public List<BBS> selectByShowOne(); // 관리자용 본인삭제 게시글 목록 출력

	public List<BBS> selectByShowTwo(); // 관리자용 관리자삭제 게시글 목록 출력

	public boolean updateShowZeroBybbsNo(int bbsNo); // 관리자 삭제 게시글 복구

	/*
	 * //페이징 public int count(); //게시물 총 갯수
	 * 
	 * public List<BBS> BBSlistPage(int rowStart, int rowEnd); //게시물 목록 + 페이징
	 */

	//페이징2
	//목록 + 페이징
	public List<BBS> listPage(Criteria cri);
	
	//게시물 총 갯수
	public int listCount();
	
	
	
	//검색 페이징 1번째 방법에 필요 
	//목록 + 페이징 + 검색     
	public List<BBS> listSearch(SearchCriteria scri);
	
	//검색 결과 갯수
	public int countSearch(SearchCriteria scri);
	
	
	
	//목록 + 페이징 + 검색 두번째방법시도중(첫번째 방법으로 다시시도중 일단 주석처리 01-03-09:53)
	//public List<BBS> listPageSearch(Criteria cri,String searchType, String keyword);
	
	//본인 삭제 글 리스트 + 페이징 + 검색 
	public List<BBS> adminShowOnelist(SearchCriteria scri);
	
	//본인삭제 검색 결과 갯수 
	public int countSearchShowOne(SearchCriteria scri);
	
	
	//관리자 삭제 글 리스트 + 페이징 + 검색 
	public List<BBS> adminShowTwolist(SearchCriteria scri);
		
	//관리자 삭제 검색 결과 갯수 
	public int countSearchShowTwo(SearchCriteria scri);
	
	//답글 쓰기 
	public Reply insertReply(Reply reply);
	
	//글번호 bbs_no에 해당하는 답글 불러오기
	public List<Reply> listReBybbsNo(int bbsNo);
	
	//전체 답글 불러오기
	public List<Reply> listRe();
	
	//답글 상세보기 
	public Reply replyView(int bbsNo);
	
	//답변 수정 
	public boolean updateRe(int bbsNo, Reply reply); 
	
	//답변 상세보기 2 (테스트 중 되면 이걸로 변경할 예정 01-09-18:54)
	public Reply replyView2(int bbsNo, int reNo);
	
	//답변 수정 2
	public boolean updateRe2(int bbsNo,int reNo, Reply reply); 
	
	
	
	
	
	
	
	
	
	
	
	
}
