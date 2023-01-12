package project3.yakdo.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.BBS.SearchCriteria;
import project3.yakdo.domain.users.SignUpForm;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsersMybatisRepository implements UsersRepository{
	
	private final UsersMapper usersMapper;

/* Insert 관련 메소드 */
	
	/**
	 * USERS 테이블에 insert 하는 메소드
	 * @param SignUpForm signUpForm
	 * @return Integer(result)
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertUsers(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsers(signUpForm);
		return result;
	}

	/**
	 * USERS_INFO, USERS_INFO_USING_DRUGS, USERS_INFO_ALLERGY 테이블에 insert 하는 메소드
	 * users_info에 insert한 후 familyNo를 구해
	 * Map에 담아 usingDrugMap과 allergyMap에 insert한다
	 * 
	 * @param SignUpForm signUpForm
	 * @return Integer(result) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertUsersInfo(UsersInfo usersInfo) {
		usersMapper.insertUsersInfo(usersInfo);
		Integer familyNo = usersMapper.selectUsersInfoByFamilyNick(usersInfo.getFamilyNick(),usersInfo.getUserNo()).getFamilyNo();
		for(String usingDrug : usersInfo.getUsingDrugList()) {
			Map<String, Object> usingDrugMap = new HashMap<>();
			usingDrugMap.put("userNo", usersInfo.getUserNo());
			usingDrugMap.put("familyNo", familyNo);
			usingDrugMap.put("usingDrug", usingDrug);
			usersMapper.insertUsingDrugs(usingDrugMap);			
		}
		for(String allergy : usersInfo.getAllergyList()) {
			Map<String, Object> allergyMap = new HashMap<>();
			allergyMap.put("userNo", usersInfo.getUserNo());
			allergyMap.put("familyNo", familyNo);
			allergyMap.put("allergy", allergy);
			usersMapper.insertAllergy(allergyMap);			
		}
		return 0;
	}

/* SELECT 관련 메소드 */
	
	/**
	 * userEmail을 전달 받아 USERS select 하기
	 * userEmail이 같고 활동중(0)인 회원 데이터만 가져온다 
	 * @param String userEmail
	 * @return Integer(result)
	 * 담당자 : 빙예은
	 */
	@Override
	public Users selectUserByUserEmail(String userEmail) {
		Users user = usersMapper.selectUserByUserEmail(userEmail);
		return user;
	}

	/**
	 * USERS에 있는 전체 DB SELECT
	 * @return List<Users> userList 
	 * 담당자 : 빙예은
	 */
	@Override
	public List<Users> selectAllUsers() {
		List<Users> userList = usersMapper.selectAllUsers();
		
		return userList;
	}
	
	/**
	 * userNo를 받아서 UsersInfo를 List방식으로 반환
	 * @param: Integer(userNo)
	 * @return: List<UsersInfo>
	 * 담당자 : 홍준표
	 */
	public List<UsersInfo> selectUsersInfoByUsersNo(Integer userNo){
		List<UsersInfo> usersInfoList = usersMapper.selectUsersInfoByUserNo(userNo);
		for(UsersInfo usersInfo : usersInfoList) {
			List<String> usingDrugList = usersMapper.selectUsingDrugByUserNoAndFamilyNo(usersInfo.getUserNo(),usersInfo.getFamilyNo());
			usersInfo.setUsingDrugList(usingDrugList);
			List<String> allergyList = usersMapper.selectAllergyByUserNoAndFamillyNo(usersInfo.getUserNo(),usersInfo.getFamilyNo());
			usersInfo.setAllergyList(allergyList);
		}
		return usersInfoList;
	}
	
/* UPDATE 관련 메소드 */
	/**
	 * 바꿀 userNick, 로그인 된 Users의 userNo를 받아서 update 실행
	 * @param String userNick
	 * @param Integer userNo
	 * @return 1 : 업데이트 성공 / 0 : 업데이트 실패
	 * 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer updateUserNickByUserNo(String userNick, Integer userNo) {
		if(usersMapper.updateUserNickByUserNo(userNick, userNo) == 1) {
			return 1;
		}
		return 0;
	}


	/**
	 * 바꿀 userPwNew, 로그인 된 Users의 userNo를 받아서 update 실행
	 * @param String userPwNew
	 * @param Integer userNo
	 * @return 1 : 업데이트 성공 / 0 : 업데이트 실패
	 * 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer updateUserPwByUserNo(String userPwNew, Integer userNo) {
		if(usersMapper.updateUserPwByUserNo(userPwNew, userNo) == 1) {
			return 1;
		}
		return null;
	}

	/**
	 * userNo 기준으로 users 테이블의 users_status를 1(탈퇴)로 변경
	 * users_info, users_info_using_drugs, users_info_allergy 테이블에서 데이터 삭제
	 * 담당자 : 빙예은
	 */
	@Override
	public void updateUserStatusLeaveByUserNo(Integer userNo) {
		if(usersMapper.updateUserStatusLeaveByUserNo(userNo) == 1) {
			usersMapper.deleteUsersInfoByUsersNo(userNo);
			usersMapper.deleteUsersInfoUsingDrugsByUsersNo(userNo);
			usersMapper.deleteUsersInfoAllergyByUsersNo(userNo);
		}
	}
	

	/**
	 * UserEmail에 맞는 회원 전체 찾기
	 * 담당자 : 빙예은
	 */
	@Override
	public List<Users> selectUserAllByUserEmail(String userEmail) {
		List<Users> userList = usersMapper.selectUserAllByUserEmail(userEmail);
		return userList;
	}

	
	/**
	 * UsersInfo 테이블의 PK값을 제외하고 내용 변경
	 * FamilyNo가 빈값이 있으면 FamilyNo가 변경되지만,
	 * 이용에 영향을 미치지 못해서 그냥 이대로 사용하기로 함. 
	 * @param: UsersInfo
	 * 담당자 : 홍준표
	 */
	public void updateUsersInfo(UsersInfo usersInfo) {
		deleteUsersInfo(usersInfo);
		insertUsersInfo(usersInfo);
	}
	
	//Users테이블에서 userNo로 회원찾기 / 담당자: 배고운 
	@Override
	public Users selectByUserNoInUsersT(Integer userNo) {
		// TODO Auto-generated method stub
		Users user = usersMapper.selectByUserNoInUsersT(userNo);
		return user;
	}
	
	
	//회원등급수정 / 담당자: 배고운 
	@Override
	public boolean updateUserGrade(Integer userNo, Users users) {
		// TODO Auto-generated method stub
		boolean result = false;
		usersMapper.updateUserGrade(userNo, users);
		return result;
	}

	
	//회원블락처리 / 담당자 : 배고운
	@Override
	public boolean updateUserStatus(Integer userNo, Users users) {
		// TODO Auto-generated method stub
		boolean result = false;
		usersMapper.updateUserStatus(userNo, users);
		return result;
	}


	//회원목록+페이징+검색 / 담당자:배고운
	@Override
	public List<Users> searchUserList(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return usersMapper.searchUserList(scri);
	}

	//검색결과갯수 / 담당자:배고운
	@Override
	public int countSearchUsers(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return usersMapper.countSearchUsers(scri);
	}
	
	/**
	 * UsersInfo 테이블에서 UserNo의 FamilyNo 정보 삭제
	 * @param UsersInfo 
	 * 
	 * 담당자 : 빙예은
	 */
	public void deleteUsersInfo(UsersInfo usersInfo) {
		usersMapper.deleteFamilyInfoByUsersNoAndFamilyNo(usersInfo);
		usersMapper.deleteFamilyUsingDrugsByUsersNoAndFamilyNo(usersInfo);
		usersMapper.deleteFamilyAllergyByUsersNoAndFamilyNo(usersInfo);
	}

	/**
	 * Users 테이블에서 userNick 가져오기
	 * @param String userNick
	 * @return db가 없을 경우 null, 있을 경우 users테이블의 userNick
	 * 
	 * 담당자 : 빙예은
	 */
	@Override
	public String selectUserNickByUserStatusAndUsesrNick(String userNick) {
		log.info(userNick);
		if(usersMapper.selectUserNickByUserStatusAndUsesrNick(userNick) == null
				|| usersMapper.selectUserNickByUserStatusAndUsesrNick(userNick).isBlank()) {
			return null;
		}
		return usersMapper.selectUserNickByUserStatusAndUsesrNick(userNick);
	}
	


}
