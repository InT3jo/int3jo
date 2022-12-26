package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.JoinForm;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsersMybatisRepository implements UsersRepository{
	
	private final UsersMapper usersMapper;
	
	@Override
	public Integer insertUsers(JoinForm joinForm) {
		Integer result = usersMapper.insertUsers(joinForm);
		return result;
	}

	@Override
	public Users selectByUserEmail(String userEmail) {
		log.info(userEmail);
		Users user = usersMapper.selectByUserEmail(userEmail);

		log.info("user {}", user);
		return user;
	}

	@Override
	public List<Users> selectAllUsers() {
		List<Users> userList = usersMapper.selectAllUsers();
		
		return userList;
	}

	@Override
	public void deleteAllUsers() {
		usersMapper.deleteAllUsers();
	}


	@Override
	public Integer insertUsersInfo(UsersInfo usersInfo) {
		Integer result = usersMapper.insertUsersInfo(usersInfo);
		return result;
	}

	@Override
	public UsersInfo selectByUserNo(Integer userNo) {
		UsersInfo usersInfo = usersMapper.selectByUserNo(userNo);
		
		return usersInfo;
	}

	@Override
	public List<UsersInfo> selectAllUsersInfo() {
		List<UsersInfo> userList = usersMapper.selectAllUsersInfo();
	
		return userList;
	}

	@Override
	public void deleteAllUsersInfo() {
		usersMapper.deleteAllUsersInfo();
	}

	
}
