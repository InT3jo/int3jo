package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.validation.form.JoinForm;

@Mapper
public interface UsersMapper {
	
	public Integer insertUsers(JoinForm joinForm);
	
	public Users selectByUserEmail(String userEmail);
	
	public List<Users> selectAllUsers();
	
	public void deleteAllUsers();

//	public Integer insertUsersInfo(UsersInfo usersInfo);
	
	public UsersInfo selectByUserNo(Integer userNo);
	
	public List<UsersInfo> selectAllUsersInfo();
	
	public void deleteAllUsersInfo();
	
}
