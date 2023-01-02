package project3.yakdo.repository.mybatis;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import project3.yakdo.domain.users.Users;
import project3.yakdo.validation.form.SignUpForm;

@Mapper
public interface UsersMapper {
	
	//회원 가입 시 Users에 insert 실행될 인터페이스
	public Integer insertUsers(SignUpForm signUpForm);
	
	//회원 가입 시 Users_info에 insert 실행될 인터페이스
	public Integer insertUsersInfo(SignUpForm signUpForm);

	//회원 가입 시 Users_Info_UsingDrugs에 insert 실행될 인터페이스
	public Integer insertUsingDrugs(Map<String, Object> usingDrugsMap);
	public Integer insertUsingDrugs(SignUpForm signUpForm);
	
	//회원 가입 시 Users_Info_Allergy에 insert 실행될 인터페이스
	public Integer insertAllergy(SignUpForm signUpForm);
	
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
