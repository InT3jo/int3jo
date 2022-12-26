package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBSComment;
import project3.yakdo.repository.BBSCommentRepository;

@Repository
@RequiredArgsConstructor
public class BBSCommentMybatisRepository implements BBSCommentRepository{
	private final BBSCommentMapper bbsCommentMapper;
	
	@Override
	public BBSComment insertBBSCom(BBSComment bbsComment) {
		// TODO Auto-generated method stub
		Integer result = bbsCommentMapper.insertBBSCom(bbsComment);
		return bbsComment;
	}

	@Override
	public List<BBSComment> selectComBybbsNo(Integer bbsNo) {
		// TODO Auto-generated method stub
		List<BBSComment> commentListZero = bbsCommentMapper.selectComBybbsNo(bbsNo);
		//리턴하기전에 BBSComment userNick까지 합쳐서 리턴 
		return commentListZero;
	}

	@Override
	public boolean updateCom(int bbsNo, BBSComment bbsComment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateComShowOneBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateComShowTwoBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		return false;
	}

}
