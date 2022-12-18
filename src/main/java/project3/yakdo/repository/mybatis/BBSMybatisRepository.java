package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.repository.BBSRepository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BBSMybatisRepository implements BBSRepository {

	private final BBSMapper BBSMapper;

	@Override
	public BBS insert(BBS BBS) {
		// TODO Auto-generated method stub
		Integer result = BBSMapper.insert(BBS);
		log.info("BBS insert result {}", result);
		
		return BBS;
	}

	@Override
	public BBS selectByBBS_no(int BBS_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BBS> selectByBBS_show_0() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(int BBS_no, BBS BBS) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBBS_show_1_ByBBS_no(int BBS_no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBBS_show_2_ByBBS_no(int BBS_no) {
		// TODO Auto-generated method stub
		return false;
	}

}
