package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.Criteria;
import project3.yakdo.domain.BBS.SearchCriteria;

public interface BBSRepository {

	public BBS insertBBS(BBS bbs); // 게시글 생성 Create

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
	
	
	//목록 + 페이징 + 검색
	public List<BBS> listSearch(SearchCriteria scri);
	
	//검색 결과 갯수
	public int countSearch(SearchCriteria scri);
	
	//목록 + 페이징 + 검색 두번째방법시도중
	public List<BBS> listPageSearch(Criteria cri,String searchType, String keyword);
	
}
