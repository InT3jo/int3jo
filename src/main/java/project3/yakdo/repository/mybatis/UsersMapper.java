package project3.yakdo.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.validation.form.SignUpForm;

@Mapper
public interface UsersMapper {
/* INSERT */
	//Users insert
	public Integer insertUsers(SignUpForm signUpForm);
	
	//Users_info insert
	public Integer insertUsersInfo(SignUpForm signUpForm);

	//Users_Info_UsingDrugs insert
	public Integer insertUsingDrugs(Map<String, Object> usingDrugMap);
	
	//Users_Info_Allergy insert
	public Integer insertAllergy(Map<String, Object> usingDrugMap);
	
/* SELECT */
	//USERS_INFO select(familyNo 1씩 증가) 기준 :  userNo
	public UsersInfo addFamilyNoByUserNo(Integer UserNo);
	
	//USERS_INFO select 기준 : familyNo, userNo
	public UsersInfo selectByFamilyNo(SignUpForm signUpForm);
	
	//Users select 기준 : userEmail
	public Users selectByUserEmail(String userEmail);

	//Users selectAll
	public List<Users> selectAllUsers();
	
	//관리자 페이지 회원관리 관련 / 작성자: 배고운 
	
	//Users테이블에서 userNo로 회원찾기 / 작성자: 배고운 
	public Users selectByUserNoInUsersT(Integer userNo);
	
	//회원등급수정 / 작성자: 배고운 
	public void updateUserGrade(@Param("userNo") Integer userNo, @Param("updateUser") Users users);

	//회원블락처리 / 작성자 : 배고운
	public void updateUserStatus(@Param("userNo")Integer userNo, @Param("updateUser")Users users);
}
