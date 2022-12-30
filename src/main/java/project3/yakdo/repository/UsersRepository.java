package project3.yakdo.repository;


import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	
	//관리자 페이지 회원관리 관련 / 작성자: 배고운 
	
	//Users테이블에서 userNo로 회원찾기 / 작성자: 배고운 
	public Users selectByUserNoInUsersT(Integer userNo);
	
	//회원등급수정 / 작성자: 배고운 
	public boolean updateUserGrade(@Param("userNo")Integer userNo, @Param("users")Users users);
	
	//회원블락처리 / 작성자 : 배고운
	public boolean updateUserStatus(@Param("userNo")Integer userNo, @Param("users")Users users);
	
	
	
}
