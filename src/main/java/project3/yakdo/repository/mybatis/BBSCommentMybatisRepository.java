package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBSComment;
import project3.yakdo.repository.BBSCommentRepository;

@Repository
@RequiredArgsConstructor
public class BBSCommentMybatisRepository implements BBSCommentRepository{@Override
	public BBSComment insertBBSCom(BBSComment BBSComment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BBSComment> selectComBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		return null;
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
