package project3.yakdo.service.BBS;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.PageMaker;
import project3.yakdo.domain.BBS.Reply;
import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.repository.BBSRepository;

@RequiredArgsConstructor
@Service
public class BBSService {
	 private final BBSRepository BBSRepository;

	/**
	 * 총 페이지수를 만들어주는 메서드
	 * @param SearchCriteria
	 * @return PageMaker
	 * 담당자: 배고운
	 */
	public PageMaker makePage(SearchCriteria scri,int count) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(count);
		return pageMaker;
	}
	/**
	 * 본인삭제글 총 갯수 
	 * @param scri
	 * @return int 
	 * 담당자 : 배고운 
	 */
	public int countSearchShowOne(SearchCriteria scri) {
		return BBSRepository.countSearchShowOne(scri);
	}
	
	/**
	 * 관리자 삭제글 총 갯수 
	 * @param scri
	 * @return int 
	 * 담당자 : 배고운 
	 */
	public int countSearchShowTwo(SearchCriteria scri) {
		return BBSRepository.countSearchShowTwo(scri);
	}
	/**
	 * 본인삭제글 리스트 출력 
	 * @param scri
	 * @return
	 * 담당자 : 배고운 
	 */
	public List<BBS> getShowOneList(SearchCriteria scri){
		return BBSRepository.adminShowOnelist(scri);	 
	}

	/**
	 * 관리자 삭제글 리스트 출력 
	 * @param scri
	 * @return
	 * 담당자 : 배고운 
	 */
	public List<BBS> getShowTwoList(SearchCriteria scri) {
		return BBSRepository.adminShowTwolist(scri);
	}

	/**
	 * 관리자 삭제 게시글 복구 
	 * @param bbsNo
	 * 담당자 : 배고운 
	 */
	public void recoverBBS(Integer bbsNo) {
		BBSRepository.updateShowZeroBybbsNo(bbsNo);
	}
	/**
	 * 이용문의 게시글 총 갯수 (정상상태게시글만)
	 * @param scri
	 * @return int
	 * 담당자 : 배고운 
	 */
	public int countSearch(SearchCriteria scri) {
		return BBSRepository.countSearch(scri);
	}
	/**
	 * 이용문의 게시글 리스트 출력 (정상상태게시글만)
	 * @param scri
	 * @return List<BBS>
	 * 담당자 : 배고운 
	 */
	public List<BBS> getBBSList(SearchCriteria scri){
		return BBSRepository.listSearch(scri);
	}
	/**
	 * 이용문의 게시판 답글 리스트 출력 
	 * @return List<Reply>
	 * 담당자 : 배고운 
	 */
	public List<Reply> getReList(){
		return BBSRepository.listRe();
	}
	
	/**
	 * 글번호에 해당하는 게시글 select 
	 * @param bbsNo
	 * @return BBS
	 * 담당자 : 배고운 
	 */
	public BBS getBBSbybbsNo(int bbsNo) {
		return BBSRepository.selectBybbsNo(bbsNo);
	}
	/**
	 * 게시글 작성 
	 * @param bbs
	 * 담당자 : 배고운 
	 */
	public void insertBBS(BBS bbs) {
		BBSRepository.insertBBS(bbs);
	}
	
	/**
	 * 게시글 수정
	 * @param bbsNo
	 * @param bbs
	 * 담당자 : 배고운 
	 */
	public void updateBBS(int bbsNo, BBS bbs) {
		 BBSRepository.updateBBS(bbsNo, bbs);
	}
	/**
	 * 게시글 본인 삭제 
	 * @param bbsNo
	 * 담당자 : 배고운 
	 */
	public void updateShowOneBybbsNo(int bbsNo) {
		BBSRepository.updateShowOneBybbsNo(bbsNo);
	}
	
	/**
	 * 게시글 관리자 삭제 
	 * @param bbsNo
	 * 담당자 : 배고운 
	 */
	public void updateShowTwoBybbsNo(int bbsNo) {
		BBSRepository.updateShowTwoBybbsNo(bbsNo);
	}
	/**
	 * 글번호에 해당하는 게시글 복구  
	 * @param bbsNo
	 * 담당자 : 배고운 
	 */
	public void recoverBBSBybbsNo(int bbsNo) {
		BBSRepository.updateShowZeroBybbsNo(bbsNo);
	}
	/**
	 * 글번호와 답변번호를 비교하여 해당하는 답변 select 
	 * @param bbsNo
	 * @param reNo
	 * @return Reply
	 * 담당자 : 배고운
	 */
	public Reply viewReply(int bbsNo, int reNo) {
		return BBSRepository.replyView2(bbsNo, reNo);
	}
	/**
	 * 답변 수정하는 메서드
	 * @param bbsNo
	 * @param reNo
	 * @param reply
	 * 담당자 : 배고운
	 */
	public void updateReply(int bbsNo, int reNo, Reply reply) {
		BBSRepository.updateRe2(bbsNo, reNo, reply);
	}
	/**
	 * 답변 삭제하는 메서드 
	 * @param bbsNo
	 * @param reNo
	 * 담당자 : 배고운 
	 */
	public void deleteReply(int bbsNo , int reNo) {
		BBSRepository.updateReShow1(bbsNo, reNo);
	}
	/**
	 * 답변 쓰는 메서드
	 * @param reply
	 * 담당자 : 배고운
	 */
	public void insertReply(Reply reply) {
		BBSRepository.insertReply(reply);
	}
	
	
}
