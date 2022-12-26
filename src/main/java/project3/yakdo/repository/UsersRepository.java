package project3.yakdo.repository;


import java.util.List;

import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.validation.form.JoinForm;

public interface UsersRepository {

	public Integer insertUsers(JoinForm joinForm);
	
	public Users selectByUserEmail(String userEmail);
	
	public List<Users> selectAllUsers();
	
	public void deleteAllUsers();
	
	public Integer insertUsersInfo(UsersInfo usersInfo);
	
	public UsersInfo selectByUserNo(Integer userNo);
	
	public List<UsersInfo> selectAllUsersInfo();
	
	public void deleteAllUsersInfo();
}
