package project3.yakdo.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.BBS.SearchCriteria;
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

	public List<Users> selectAllUsers();
	
	
	//관리자 페이지 회원관리 관련 / 작성자: 배고운 
	
	//Users테이블에서 userNo로 회원찾기 / 작성자: 배고운 
	public Users selectByUserNoInUsersT(Integer userNo);
	
	//회원등급수정 / 작성자: 배고운 
	public void updateUserGrade(@Param("userNo") Integer userNo, @Param("updateUser") Users users);

	//회원블락처리 / 작성자 : 배고운
	public void updateUserStatus(@Param("userNo")Integer userNo, @Param("updateUser")Users users);
	
	//회원 목록 + 페이징 + 검색 / 작성자: 배고운 
	public List<Users> searchUserList(SearchCriteria scri);
	
	//검색 결과 갯수 / 작성자 : 배고운 
	public int countSearchUsers(SearchCriteria scri);
}
