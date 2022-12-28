package project3.yakdo.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import project3.yakdo.domain.users.Users;
import project3.yakdo.validation.form.JoinForm;

@Mapper
public interface UsersMapper {
	
	//회원 가입 시 Users에 insert 실행될 인터페이스
	public Integer insertUsers(JoinForm joinForm);
	
	//회원 가입 시 Users_info에 insert 실행될 인터페이스
	public Integer insertUsersInfo(JoinForm joinForm);
	
	//회원 가입 시 Users_Info_Allergy에 insert 실행될 인터페이스
	public Integer insertUsersInfoAllergy(JoinForm joinForm);
	
	//회원 가입 시 Users_Info_UsingDrugs에 insert 실행될 인터페이스
	public Integer insertUsersUsingDrugs(JoinForm joinForm);
	
	public Users selectByUserEmail(String userEmail);
//	
//	public List<Users> selectAllUsers();
//	
//	public void deleteAllUsers();
//
//	public UsersInfo selectByUserNo(Integer userNo);
//	
//	public List<UsersInfo> selectAllUsersInfo();
//	
//	public void deleteAllUsersInfo();
	
}
