package project3.yakdo.repository;


import java.util.List;

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
	//Users 테이블에서 userEmail로 회원 찾기 기준 : userEmail, userStatus = 0(활동중) / 담당자 : 빙예은
	public Users selectUserByUserEmail(@Param("userEmail") String userEmail);
	
	//Users 데이터 List로 받기 / 담당자 : 빙예은
	public List<Users> selectAllUsers();
	
	//UserEmail에 맞는 회원 전체 찾기 / 담당자 : 빙예은
	public List<Users> selectUserAllByUserEmail(String userEmail);

	// UsersInfoList 가져오기 / 담당자 : 홍준표
	public List<UsersInfo> selectUsersInfoByUsersNo(Integer userNo);
	
	// Users 테이블에서 활동중인 회원의 userNick 가져오기 / 담당자 : 빙예은
	public String selectUserNickByUserStatusAndUsesrNick(String userNick);
	
/* UPDATE */
	//Users 테이블의 userNick Update / 담당자 : 빙예은
	public Integer updateUserNickByUserNo(@Param("userNick") String userNick, @Param("userNo") Integer userNo);
	
	//Users 테이블의 userPw Update / 담당자 : 빙예은
	public Integer updateUserPwByUserNo(@Param("userPwNew") String userPwNew, @Param("userNo") Integer userNo);
	
	//Users 테이블의 userStatus Update / 담당자 : 빙예은
	public void updateUserStatusLeaveByUserNo(@Param("userNo") Integer userNo);

	//UsersInfo 테이블의 PK값을 제외하고 내용 변경 / 담당자 : 홍준표
	public void updateUsersInfo(UsersInfo usersInfo);
	
/* 회원 관리 */
	
	//Users테이블에서 userNo로 회원찾기 / 담당자: 배고운 
	public Users selectByUserNoInUsersT(Integer userNo);
	
	//Users테이블의 userGrade, userStatus 회원등급,상태 수정 / 담당자: 배고운 
	public boolean updateUserGrade(@Param("userNo")Integer userNo, @Param("users")Users users);

	
	//회원 목록 + 페이징 + 검색 / 담당자: 배고운 
	public List<Users> searchUserList(SearchCriteria scri);
	
	//검색 결과 갯수 / 담당자 : 배고운 
	public int countSearchUsers(SearchCriteria scri);
	
	//UsersInfo 테이블에서 UserNo의 FamilyNo 정보 삭제 
	public void deleteUsersInfo(UsersInfo usersInfo);
	
	
	


}
