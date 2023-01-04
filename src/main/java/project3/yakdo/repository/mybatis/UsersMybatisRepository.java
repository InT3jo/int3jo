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
	 * USERS 테이블에 파라미터의 객체 추가
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
	 * USERS_INFO 테이블에 insert 하는 메소드
	 * @param SignUpForm signUpForm
	 * @return Integer(result) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertUsersInfo(UsersInfo usersInfo) {
		Integer result = usersMapper.insertUsersInfo(usersInfo);
		Integer familyNo = usersMapper.selectUsersInfoByFamilyNick(usersInfo.getFamilyNick(),usersInfo.getUserNo()).getFamilyNo();
		for(String usingDrug : usersInfo.getUsingDrugList()) {
			Map<String, Object> usingDrugMap = new HashMap<>();
			usingDrugMap.put("userNo", usersInfo.getUserNo());
			usingDrugMap.put("familyNo", familyNo);
			usingDrugMap.put("usingDrug", usingDrug);
			Integer result2 = usersMapper.insertUsingDrugs(usingDrugMap);			
		}
		for(String allergy : usersInfo.getAllergyList()) {
			Map<String, Object> allergyMap = new HashMap<>();
			allergyMap.put("userNo", usersInfo.getUserNo());
			allergyMap.put("familyNo", familyNo);
			allergyMap.put("allergy", allergy);
			Integer result3 = usersMapper.insertAllergy(allergyMap);			
		}
		return result;
	}

	/**
	 * USERS_INFO_USING_DRUGS insert 하는 메소드
	 * SingUpForm을 파라미터로 받아서 usingDrugList List 생성
	 * List의 약물을 Map에 저장한 후 insert 진행
	 * 
	 * @param SignUpForm signUpForm
	 * @return Integer(result) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertUsingDrugs(SignUpForm signUpForm) {
//		Map<String, Object> usingDrugMap = new LinkedHashMap<>();
//		List<String> drugList = signUpForm.getUsingDrugList();
//		
		int result = 0;
//		for(int i = 0; i<drugList.size(); i++) {
//			usingDrugMap.put("familyNo", signUpForm.getFamilyNo());
//			usingDrugMap.put("usingDrug", drugList.get(i));
//			result = usersMapper.insertUsingDrugs(usingDrugMap);
//		}
		return result;
	}
	
	/**
	 * USERS_INFO_ALLERGY insert 하는 메소드
	 * SingUpForm을 파라미터로 받아서 allergyList List 생성
	 * List의 알러지를 Map에 저장한 후 insert 진행
	 * 
	 * @param SignUpForm signUpForm
	 * @return Integer(result) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertAllergy(SignUpForm signUpForm) {
//		Map<String, Object> allergyMap = new LinkedHashMap<>();
//		List<String> allergyList = signUpForm.getAllergyList();
//		
		int result = 0;
//		for(int i = 0; i<allergyList.size(); i++) {
//			allergyMap.put("familyNo", signUpForm.getFamilyNo());
//			allergyMap.put("allergy", allergyList.get(i));
//			result = usersMapper.insertAllergy(allergyMap);
//		}
		return result;
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


	
	//관리자 페이지 회원관리 관련 - 작성자: 배고운 
	
	//Users테이블에서 userNo로 회원찾기 / 작성자: 배고운 
	@Override
	public Users selectByUserNoInUsersT(Integer userNo) {
		// TODO Auto-generated method stub
		Users user = usersMapper.selectByUserNoInUsersT(userNo);
		return user;
	}
	
	
	//회원등급수정 / 작성자: 배고운 
	@Override
	public boolean updateUserGrade(Integer userNo, Users users) {
		// TODO Auto-generated method stub
		boolean result = false;
		usersMapper.updateUserGrade(userNo, users);
		return result;
	}

	
	//회원블락처리 / 작성자 : 배고운
	@Override
	public boolean updateUserStatus(Integer userNo, Users users) {
		// TODO Auto-generated method stub
		boolean result = false;
		usersMapper.updateUserStatus(userNo, users);
		return result;
	}


	//회원목록+페이징+검색 / 작성자:배고운
	@Override
	public List<Users> searchUserList(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return usersMapper.searchUserList(scri);
	}

	//검색결과갯수 / 작성자:배고운
	@Override
	public int countSearchUsers(SearchCriteria scri) {
		// TODO Auto-generated method stub
		return usersMapper.countSearchUsers(scri);
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

}
