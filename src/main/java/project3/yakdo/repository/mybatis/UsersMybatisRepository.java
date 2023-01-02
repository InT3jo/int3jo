package project3.yakdo.repository.mybatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.SignUpForm;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsersMybatisRepository implements UsersRepository{
	
	private final UsersMapper usersMapper;
	
	@Override
	public Integer insertUsers(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsers(signUpForm);
		return result;
	}

	@Override
	public Integer insertUsersInfo(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsersInfo(signUpForm);
		return result;
	}

//	@Override
//	public Integer insertUsingDrugs(Map<String, Object> usingDrugsMap) {
//		Integer result = usersMapper.insertUsingDrugs(usingDrugsMap);
//		return result;
//	}
	@Override
	public Integer insertUsingDrugs(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsingDrugs(signUpForm);
		return result;
	}
	
	@Override
	public Integer insertAllergy(SignUpForm signUpForm) {
		Integer result = usersMapper.insertAllergy(signUpForm);
		return result;
	}


	@Override
	public Users selectByUserEmail(String userEmail) {
		log.info(userEmail);
		Users user = usersMapper.selectByUserEmail(userEmail);

		log.info("user {}", user);
		return user;
	}
//
//	@Override
//	public List<Users> selectAllUsers() {
//		List<Users> userList = usersMapper.selectAllUsers();
//		
//		return userList;
//	}
//
//	@Override
//	public UsersInfo selectByUserNo(Integer userNo) {
//		UsersInfo usersInfo = usersMapper.selectByUserNo(userNo);
//		
//		return usersInfo;
//	}
//
//	@Override
//	public List<UsersInfo> selectAllUsersInfo() {
//		List<UsersInfo> userList = usersMapper.selectAllUsersInfo();
//	
//		return userList;
//	}

//	@Override
//	public void deleteAllUsers() {
//		usersMapper.deleteAllUsers();
//	}
//
//	@Override
//	public void deleteAllUsersInfo() {
//		usersMapper.deleteAllUsersInfo();
//	}

}
