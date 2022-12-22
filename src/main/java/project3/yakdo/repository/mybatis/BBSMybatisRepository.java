package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.domain.BBS.BBSInfo;
import project3.yakdo.repository.BBSRepository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BBSMybatisRepository implements BBSRepository {

	private final BBSMapper BBSMapper;

	
	//게시판
	@Override
	public BBS insertBBS(BBS bbs) {
		// TODO Auto-generated method stub
		Integer result = BBSMapper.insertBBS(bbs);
		return bbs;
	}

	@Override
	public BBS selectBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		BBS bbs = BBSMapper.selectBybbsNo(bbsNo);
		return bbs;
	}

	@Override
	public List<BBS> selectByShowZero() {
		// TODO Auto-generated method stub
		List<BBS> bbsListZero = null;
		bbsListZero = BBSMapper.selectByShowZero();
		return bbsListZero;
	}

	@Override
	@Transactional
	public boolean updateBBS(int bbsNo, BBS bbs) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			BBSMapper.updateBBS(bbsNo, bbs);
			result = true;
			
			
			
	
		} catch (Exception e) {
			log.error("BBSMapper update error {} {}",bbsNo, bbs);
		}
		return result;
			
		
	
	}

	@Override
	public boolean updateShowOneBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateShowTwoBybbsNo(int bbsNo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	//관리자 
	@Override
	public List<BBS> selectByShowOne() {
		// TODO Auto-generated method stub
		List<BBS> bbsListOne = null;
		bbsListOne = BBSMapper.selectByShowOne();
		return bbsListOne;
	}

	@Override
	public List<BBS> selectByShowTwo() {
		// TODO Auto-generated method stub
		List<BBS> bbsListTwo = null;
		bbsListTwo = BBSMapper.selectByShowTwo();
		return bbsListTwo;
	}

	


}
