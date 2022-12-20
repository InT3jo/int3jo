package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

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
		BBS result = BBSMapper.selectByBBS_no(BBS_no);
		return result;
	}

	@Override
	public List<BBS> selectByBBS_show_0() {
		// TODO Auto-generated method stub
		List<BBS> BBSList = null;
		try {
			BBSList = BBSMapper.selectByBBS_show_0();
			log.info(BBSList.toString());
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		return BBSList;

	}

	@Override
	public boolean update(int BBS_no, BBS bbs) {
		// TODO Auto-generated method stub
		BBSMapper.update(BBS_no,bbs);
		return false;
	}

	@Override
	public boolean updateBBS_show_1_ByBBS_no(int BBS_no) {
		// TODO Auto-generated method stub
		BBSMapper.updateBBS_show_1_ByBBS_no(BBS_no);
		return false;
	}

	@Override
	public boolean updateBBS_show_2_ByBBS_no(int BBS_no) {
		// TODO Auto-generated method stub
		BBSMapper.updateBBS_show_2_ByBBS_no(BBS_no);
		return false;
	}

	@Override
	public List<BBSInfo> selectBBSInfoByBBS_show_0() {
		// TODO Auto-generated method stub
		List<BBSInfo> BBSInfoList = null;
		BBSInfoList = BBSMapper.selectBBSInfoByBBS_show_0();
		return BBSInfoList;
	}

}
