package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBSComment;
import project3.yakdo.repository.BBSCommentRepository;
@Slf4j
@Repository
@RequiredArgsConstructor
public class BBSCommentMybatisRepository implements BBSCommentRepository{
	private final BBSCommentMapper bbsCommentMapper;
	
	
	/**
	 * 댓글 작성 
	 * @param BBSComment
	 * @return BBSComment
	 * 담당자 : 배고운 
	 */
	@Override
	public BBSComment insertBBSCom(BBSComment bbsComment) {
		// TODO Auto-generated method stub
		Integer result = bbsCommentMapper.insertBBSCom(bbsComment);
		return bbsComment;
	}

	
	/**
	 * bbsNo로 해당 글에 해당하는 댓글 목록 불러오기
	 * @param Integer bbsNo
	 * @return List<BBSComment>
	 * 담당자 : 배고운 
	 */
	@Override
	public List<BBSComment> selectComBybbsNo(Integer bbsNo) {
		// TODO Auto-generated method stub
		List<BBSComment> commentListZero = bbsCommentMapper.selectComBybbsNo(bbsNo);
		System.out.println(commentListZero.toString());
		//리턴하기전에 BBSComment userNick까지 합쳐서 리턴 
		return commentListZero;
	}

	
	/**
	 * 댓글 수정 
	 * @param int bbsNo, int comNo,BBSComment 
	 *  담당자 : 배고운 
	 */
	@Override
	public boolean updateCom(int bbsNo, int comNo,BBSComment bbsComment) {
		// TODO Auto-generated method stub
		boolean result = false;
		bbsCommentMapper.updateCom(bbsNo,comNo, bbsComment);
		return result;
	}


	/**
	 * 댓글 본인삭제 
	 * @param Integer bbsNo,Integer comNo
	 * 담당자 : 배고운 
	 */
	@Override
	public boolean updateComShowOneByBbsNo(Integer bbsNo, Integer comNo) {
		// TODO Auto-generated method stub
		boolean result = false;
			bbsCommentMapper.updateComShowOneBbn(bbsNo, comNo);

		return result;
	}

	
	/**
	 * 댓글 관리자 삭제 
	 * @param Integer bbsNo,Integer comNo
	 * 담당자 : 배고운 
	 */
	@Override
	public boolean updateComShowTwoBybbsNo(Integer bbsNo,Integer comNo) {
		// TODO Auto-generated method stub
		boolean result = false;
		
			bbsCommentMapper.updateComShowTwoBybbsNo(bbsNo, comNo);
		return result;
	}

	 
	/**
	 * 게시글 댓글 리스트 중 comNo로 특정 댓글 select
	 * @param int bbsNo, int comNo
	 * @return BBSComment
	 * 담당자 : 배고운 
	 */
	@Override
	public BBSComment selectOneCom(int bbsNo, int comNo) {
		// TODO Auto-generated method stub
		BBSComment bbsComment = bbsCommentMapper.selectOneCom(bbsNo, comNo);
		return bbsComment;
	}

	
}
