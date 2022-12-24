package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersInfoRepository;

@Repository
@RequiredArgsConstructor
public class UsersInfoMybatisRepository implements UsersInfoRepository {

	private final UsersInfoMapper usersInfoMapper;
	@Override
	public UsersInfo insert(UsersInfo usersInfo) {
		usersInfoMapper.insert(usersInfo);
		
		return usersInfo;
	}

	@Override
	public UsersInfo selectByUserNo(Integer userNo) {
		UsersInfo usersInfo = usersInfoMapper.selectByUserNo(userNo);
		
		return usersInfo;
	}

	@Override
	public List<UsersInfo> selectAll() {
		List<UsersInfo> userList = usersInfoMapper.selectAll();
	
	return userList;
	}

	@Override
	public void deleteAll() {
		usersInfoMapper.deleteAll();
	}

}
