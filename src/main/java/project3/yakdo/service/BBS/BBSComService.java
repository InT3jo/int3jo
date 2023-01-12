package project3.yakdo.service.BBS;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBSComment;
import project3.yakdo.repository.BBSCommentRepository;
@RequiredArgsConstructor
@Service
public class BBSComService {
	
	private final BBSCommentRepository bbsCommentRepositoy;
	
	/**
	 * 해당 게시글의 댓글리스트 불러오기
	 * @param bbsNo
	 * @return
	 * 담당자 : 배고운 
	 */
	public List<BBSComment> BBSComListbybbsNo(int bbsNo){
		return bbsCommentRepositoy.selectComBybbsNo(bbsNo);
	}
	/**
	 * 댓글 작성
	 * @param bbsComment
	 * 담당자 : 배고운 
	 */
	public void insertBBSCom(BBSComment bbsComment) {
		bbsCommentRepositoy.insertBBSCom(bbsComment);
	}
	/**
	 * 댓글 본인 삭제 
	 * @param bbsNo
	 * @param comNo
	 * 담당자: 배고운
	 */
	public void deleteCom(int bbsNo, int comNo) {
		bbsCommentRepositoy.updateComShowOneByBbsNo(bbsNo, comNo);
	}
	/**
	 * 글번호와 일치하는 게시글에 달린 특정 댓글 1개 select 
	 * @param bbsNo
	 * @param comNo
	 * @return
	 * 담당자: 배고운
	 */
	public BBSComment getComment(int bbsNo, int comNo) {
	 return bbsCommentRepositoy.selectOneCom(bbsNo, comNo);
	}
	/**
	 * 댓글 수정하기 
	 * @param bbsNo
	 * @param comNo
	 * @param bbsComment
	 * 담당자: 배고운
	 */
	public void updateCom(int bbsNo, int comNo, BBSComment bbsComment) {
		bbsCommentRepositoy.updateCom(bbsNo, comNo, bbsComment);
	}
	/**
	 * 댓글 관리자 삭제 
	 * @param bbsNo
	 * @param comNo
	 * 담당자 : 배고운
	 */
	public void deleteComByAdmin(int bbsNo, int comNo) {
		bbsCommentRepositoy.updateComShowTwoBybbsNo(bbsNo, comNo);
	}
	
	
}
