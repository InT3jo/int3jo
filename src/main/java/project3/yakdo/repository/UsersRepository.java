package project3.yakdo.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.domain.users.SignUpForm;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;

public interface UsersRepository {

/* INSERT */
	//회원 기본 정보 추가 / 담당자 : 빙예은
	public Integer insertUsers(SignUpForm signUpForm);
	//회원 가족 건강 정보 추가 / 담당자 : 빙예은
	public Integer insertUsersInfo(UsersInfo usersInfo);
	
/* SELECT */
	//Users 테이블에서 userEmail로 회원 찾기 / 담당자 : 빙예은
	public Users selectUserByUserEmail(String userEmail);
	
	//Users 데이터 List로 받기 / 담당자 : 빙예은
	public List<Users> selectAllUsers();
	
/* UPDATE */
	//Users 테이블의 userNick Update / 담당자 : 빙예은
	public Integer updateUserNickByUserNo(@Param("userNick") String userNick,@Param("userNo") Integer userNo);
	
	//Users 테이블의 userPw Update / 담당자 : 빙예은
	public Integer updateUserPwByUserNo(@Param("userPwNew") String userPwNew, @Param("userNo") Integer userNo);
	
	//Users 테이블의 userStatus Update / 담당자 : 빙예은
	public Integer updateUserStatusLeaveByUserNo(@Param("userNo") Integer userNo);
	
	//관리자 페이지 회원관리 관련 / 작성자: 배고운 
	
	//Users테이블에서 userNo로 회원찾기 / 작성자: 배고운 
	public Users selectByUserNoInUsersT(Integer userNo);
	
	//회원등급수정 / 작성자: 배고운 
	public boolean updateUserGrade(@Param("userNo")Integer userNo, @Param("users")Users users);
	
	//회원블락처리 / 작성자 : 배고운
	public boolean updateUserStatus(@Param("userNo")Integer userNo, @Param("users")Users users);

	
	//회원 목록 + 페이징 + 검색 / 작성자: 배고운 
	public List<Users> searchUserList(SearchCriteria scri);
	
	//검색 결과 갯수 / 작성자 : 배고운 
	public int countSearchUsers(SearchCriteria scri);
	
	// UsersInfoList 가져오기 / 담당자 : 홍준표
	public List<UsersInfo> selectUsersInfoByUsersNo(Integer userNo);
	


}
