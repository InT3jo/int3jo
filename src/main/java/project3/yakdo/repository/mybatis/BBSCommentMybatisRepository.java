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
	
	//댓글 insert 
	@Override
	public BBSComment insertBBSCom(BBSComment bbsComment) {
		// TODO Auto-generated method stub
		Integer result = bbsCommentMapper.insertBBSCom(bbsComment);
		return bbsComment;
	}

	//해당 글에 해당하는 댓글 목록 불러오기
	@Override
	public List<BBSComment> selectComBybbsNo(Integer bbsNo) {
		// TODO Auto-generated method stub
		List<BBSComment> commentListZero = bbsCommentMapper.selectComBybbsNo(bbsNo);
		System.out.println(commentListZero.toString());
		//리턴하기전에 BBSComment userNick까지 합쳐서 리턴 
		return commentListZero;
	}

	//댓글 수정
	@Override
	public boolean updateCom(int bbsNo, BBSComment bbsComment) {
		// TODO Auto-generated method stub
		return false;
	}

	//댓글 본인삭제 
	@Override
	public boolean updateComShowOneByBbsNo(Integer bbsNo, Integer comNo) {
		// TODO Auto-generated method stub
		boolean result = false;
//		try {
			bbsCommentMapper.updateComShowOneBbn(bbsNo, comNo);
//			result = true;
//		} catch(Exception e) {
			
//		}
		return result;
	}

	//댓글 관리자 삭제 
	@Override
	public boolean updateComShowTwoBybbsNo(Integer bbsNo,int comNo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			bbsCommentMapper.updateComShowTwoBybbsNo(bbsNo, comNo);
			result = true;
		} catch(Exception e) {
			
		}
		return result;
	}

}
