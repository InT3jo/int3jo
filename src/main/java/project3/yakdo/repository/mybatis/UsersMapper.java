package project3.yakdo.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.domain.users.SignUpForm;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;

@Mapper
public interface UsersMapper {
/* INSERT */
	//Users insert
	public Integer insertUsers(SignUpForm signUpForm);
	
	//Users_info insert
	public Integer insertUsersInfo(UsersInfo usersInfo);

	//Users_Info_UsingDrugs insert
	public Integer insertUsingDrugs(Map<String, Object> usingDrugMap);
	
	//Users_Info_Allergy insert
	public Integer insertAllergy(Map<String, Object> allergyMap);
	
/* SELECT */
	//Users select 기준 : userEmail
	public Users selectUserByUserEmail(@Param("userEmail") String userEmail);

	//Users selectAll
	public List<Users> selectAllUsers();

	//Users_info테이블에서 family_nick, user_no로 회원 정보 찾기 / 담당자 : 빙예은
	public UsersInfo selectUsersInfoByFamilyNick(@Param("familyNick") String familyNick, @Param("userNo") Integer userNo);
	
/* UPDATE */
	//Users 테이블의 userNick Update
	public Integer updateUserNickByUserNo(@Param("userNick") String userNick, @Param("userNo")Integer userNo);
	
	//Users 테이블의 userPw Update
	public Integer updateUserPwByUserNo(@Param("userPwNew") String userPwNew, @Param("userNo") Integer userNo);
	
	//Users 테이블의 user_status 상태 1(탈퇴)로 변경
	public Integer updateUserStatusLeaveByUserNo(@Param("userNo") Integer userNo);
	
/* DELETE */
	//Users_info 테이블 데이터 삭제
	public void deleteUsersInfoByUsersNo(@Param("userNo") Integer userNo);
	
	//Users_info_using_drugs 테이블 데이터 삭제
	public void deleteUsersInfoUsingDrugsByUsersNo(@Param("userNo") Integer userNo);

	//Users_info_allergy 테이블 데이터 삭제
	public void deleteUsersInfoAllergyByUsersNo(@Param("userNo") Integer userNo);
	
	
	
	//Users테이블에서 userNo로 회원찾기 / 담당자: 배고운 
	public Users selectByUserNoInUsersT(Integer userNo);
	
	//회원등급수정 / 담당자: 배고운 
	public void updateUserGrade(@Param("userNo") Integer userNo, @Param("updateUser") Users users);

	//회원블락처리 / 담당자 : 배고운
	public void updateUserStatus(@Param("userNo")Integer userNo, @Param("updateUser")Users users);

	
	//회원 목록 + 페이징 + 검색 / 담당자: 배고운 
	public List<Users> searchUserList(SearchCriteria scri);
	
	//검색 결과 갯수 / 담당자 : 배고운 
	public int countSearchUsers(SearchCriteria scri);


	// UsersInfo 리스트 가져오기 / 담당자 : 홍준표
	public List<UsersInfo> selectUsersInfoByUserNo(@Param("userNo") Integer userNo);

	// UsingDrug 리스트 가져오기 / 담당자 : 홍준표
	public List<String> selectUsingDrugByUserNoAndFamilyNo(@Param("userNo") Integer userNo, @Param("familyNo") Integer familyNo);
	
	// Allergy 리스트 가져오기 / 담당자 : 홍준표
	public List<String> selectAllergyByUserNoAndFamillyNo(@Param("userNo")Integer userNo, @Param("familyNo") Integer familyNo);


}
