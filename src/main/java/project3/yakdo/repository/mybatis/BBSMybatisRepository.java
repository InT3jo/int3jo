package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.Criteria;
import project3.yakdo.domain.BBS.Reply;
import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.repository.BBSRepository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class BBSMybatisRepository implements BBSRepository {

	private final BBSMapper BBSMapper;

	/**
	 * BBS 테이블에 insert 하는 메소드
	 * @param BBS 
	 * @return BBS
	 * 담당자 : 배고운 
	 */
	@Override
	public BBS insertBBS(BBS bbs) {
		// TODO Auto-generated method stub
		Integer result = BBSMapper.insertBBS(bbs);
		return bbs;
	}
	
	
	/**
	 * bbsNo(글번호)에 해당하는 게시글 select 하는 메소드
	 * @param int bbsNo
	 * @return BBS
	 * 담당자 : 배고운 
	 */
	@Override
	public BBS selectBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		BBS bbs = BBSMapper.selectBybbsNo(bbsNo);
		return bbs;
	}


	/**
	 * 이용문의 게시글 리스트 불러오기
	 * @return List<BBS>
	 * 담당자 : 배고운 
	 */
	@Override
	public List<BBS> selectByShowZero() {
		// TODO Auto-generated method stub
		List<BBS> bbsListZero = null;
		bbsListZero = BBSMapper.selectByShowZero();
		return bbsListZero;
	}

	/**
	 * 게시글 수정
	 * @param int bbsNo, BBS
	 * 담당자 : 배고운 
	 */
	@Override
	public boolean updateBBS(int bbsNo, BBS bbs) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			BBSMapper.updateBBS(bbsNo, bbs);
			result = true;
		} catch (Exception e) {
			
		}
		return result;
	}


	/**
	 * 게시글 본인 삭제
	 * @param int bbsNo
	 * 담당자 : 배고운 
	 */
	@Override
	public boolean updateShowOneBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			BBSMapper.updateShowOneBybbsNo(bbsNo);
			result = true;
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 게시글 관리자 삭제
	 * @param int bbsNo
	 * 담당자 : 배고운 
	 */
	@Override
	public boolean updateShowTwoBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			BBSMapper.updateShowTwoBybbsNo(bbsNo);
			result = true;
		} catch (Exception e) {

		}
		return result;
	}


	/**
	 * 본인 삭제 게시글 리스트 출력
	 * @return List<BBS>
	 * 담당자 : 배고운 
	 */
	@Override
	public List<BBS> selectByShowOne() {
		// TODO Auto-generated method stub
		List<BBS> bbsListOne = null;
		bbsListOne = BBSMapper.selectByShowOne();
		return bbsListOne;
	}

	
	/**
	 * 관리자 삭제 게시글 리스트 출력
	 * @return List<BBS>
	 * 담당자 : 배고운 
	 */
	@Override
	public List<BBS> selectByShowTwo() {
		// TODO Auto-generated method stub
		List<BBS> bbsListTwo = null;
		bbsListTwo = BBSMapper.selectByShowTwo();
		return bbsListTwo;
	}

	/**
	 * 관리자 삭제 게시글 복구
	 * @param int bbsNo
	 * 담당자 : 배고운 
	 */
	@Override
	public boolean updateShowZeroBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		boolean result = false;
		BBSMapper.updateShowZeroBybbsNo(bbsNo);
		return result;
	}




	/**
	 * 이용문의 게시글 목록 페이징 + 검색 
	 * @param SearchCriteria
	 * @return List<BBS>
	 * 담당자 : 배고운 
	 */
	@Override
	public List<BBS> listSearch(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return BBSMapper.listSearch(scri);
	}

	/**
	 * 이용문의 게시글목록 검색결과 총 갯수 
	 * @param SearchCriteria
	 * @return int
	 *  담당자 : 배고운 
	 */
	@Override
	public int countSearch(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return BBSMapper.countSearch(scri);
	}

	/**
	 * 본인삭제 게시글 목록 페이징 + 검색 
	 * @param SearchCriteria
	 * @return List<BBS>
	 * 담당자 : 배고운 
	 */
	@Override
	public List<BBS> adminShowOnelist(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return BBSMapper.adminShowOnelist(scri);
	}

	/**
	 * 본인삭제 게시글목록 검색결과 총 갯수 
	 * @param SearchCriteria
	 * @return int
	 *  담당자 : 배고운 
	 */
	@Override
	public int countSearchShowOne(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return BBSMapper.countSearchShowOne(scri);
	}

	/**
	 * 관리자삭제 게시글 목록 페이징 + 검색 
	 * @param SearchCriteria
	 * @return List<BBS>
	 * 담당자 : 배고운 
	 */
	@Override
	public List<BBS> adminShowTwolist(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return BBSMapper.adminShowTwolist(scri);
	}

	/**
	 * 관리자삭제 게시글목록 검색결과 총 갯수 
	 * @param SearchCriteria
	 * @return int
	 *  담당자 : 배고운 
	 */
	@Override
	public int countSearchShowTwo(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return BBSMapper.countSearchShowTwo(scri);
	}

	
	/**
	 * 답변 작성 
	 * @param Reply
	 * @return Reply
	 * 담당자: 배고운 
	 */
	@Override
	public Reply insertReply(Reply reply) {
		// TODO Auto-generated method stub
		Integer result = BBSMapper.insertReply(reply);
		return reply;
	}


	/**
	 * 글번호에 해당하는 답글 불러오기 
	 * @param int bbsNo
	 * @return List<Reply>
	 * 담당자: 배고운 
	 */
	@Override
	public List<Reply> listReBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		List<Reply> reList = BBSMapper.listReBybbsNo(bbsNo);
		return reList;
	}

	
	/**
	 * 답글 전체 리스트 불러오기 
	 * @return List<Reply>
	 * 담당자: 배고운 
	 */
	@Override
	public List<Reply> listRe() {
		// TODO Auto-generated method stub
		List<Reply> listRe = BBSMapper.listRe();
		return listRe;
	}


	
	/**
	 * 답변 상세보기
	 * @return Reply
	 * 담당자 : 배고운 
	 */
	@Override
	public Reply replyView2(int bbsNo, int reNo) {
		// TODO Auto-generated method stub
		Reply reply = BBSMapper.replyView2(bbsNo, reNo);
		return reply;
	}

	/**
	 * 답변 수정
	 * 담당자 : 배고운 
	 */
	@Override
	public boolean updateRe2(int bbsNo, int reNo, Reply reply) {
		// TODO Auto-generated method stub
		boolean result = false;
		BBSMapper.updateRe2(bbsNo, reNo, reply);
		return result;
	}

	/**
	 * 답변 삭제 
	 * 담당자 : 배고운 
	 */
	@Override
	public boolean updateReShow1(int bbsNo, int reNo) {
		// TODO Auto-generated method stub
		boolean result = false;
		BBSMapper.updateReShow1(bbsNo, reNo);
		return result;
	}


	
}
