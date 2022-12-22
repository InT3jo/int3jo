package project3.yakdo.service.BBS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.BBS.BBS;
import project3.yakdo.repository.BBSRepository;
import project3.yakdo.repository.mybatis.BBSMapper;

@RequiredArgsConstructor
@Service
public class BBSService {
	 private final BBSRepository BBSRepository;
//	 private BBSMapper BBSMapper;
	 
	public List<BBS> selectByBBS_show_0()
	{
		List<BBS> bbslist = BBSRepository.selectByShowZero();
		
		return bbslist;
	}
	
//	public List<BBS> selectByBBS_show_0()
//	{
//		List<BBS> BBSlist = BBSMapper.selectByBBS_show_0();
//		
//		return BBSlist;
//	}
	
	
}
