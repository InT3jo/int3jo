package project3.yakdo.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.validation.form.SignUpForm;

public interface UsersRepository {

	/* INSERT 담당자 : 빙예은 */
	public Integer insertUsers(SignUpForm signUpForm);
	public Integer insertUsersInfo(SignUpForm signUpForm);
	public Integer insertUsingDrugs(SignUpForm signUpForm);
	public Integer insertAllergy(SignUpForm signUpForm);
	
	/* SELECT 담당자 : 빙예은 */
	public UsersInfo addFamilyNoByUserNo(Integer UserNo);
	public UsersInfo selectByFamilyNo(SignUpForm signUpForm);
	public Users selectByUserEmail(String userEmail);
	public List<Users> selectAllUsers();
	
	//관리자 페이지 회원관리 관련 / 작성자: 배고운 
	
	//Users테이블에서 userNo로 회원찾기 / 작성자: 배고운 
	public Users selectByUserNoInUsersT(Integer userNo);
	
	//회원등급수정 / 작성자: 배고운 
	public boolean updateUserGrade(@Param("userNo")Integer userNo, @Param("users")Users users);
	
	//회원블락처리 / 작성자 : 배고운
	public boolean updateUserStatus(@Param("userNo")Integer userNo, @Param("users")Users users);

}
