package project3.yakdo.repository.mybatis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.SignUpForm;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsersMybatisRepository implements UsersRepository{
	
	private final UsersMapper usersMapper;

	/**
	 * 기본 정보 insert 하는 메소드
	 * @return result (Integer) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertUsers(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsers(signUpForm);
		return result;
	}

	/**
	 * 가족 정보 insert 하는 메소드
	 * @return result (Integer) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertUsersInfo(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsersInfo(signUpForm);
		return result;
	}

	/**
	 * 복용 중인 약물 insert 하는 메소드
	 * @return result (Integer) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertUsingDrugs(SignUpForm signUpForm) {
		Map<String, Object> usingDrugMap = new LinkedHashMap<>();
		List<String> drugList = signUpForm.getUsingDrugList();
		
		int result = 0;
		for(int i = 0; i<drugList.size(); i++) {
			usingDrugMap.put("familyNo", signUpForm.getFamilyNo());
			usingDrugMap.put("usingDrug", drugList.get(i));
			result = usersMapper.insertUsingDrugs(usingDrugMap);
		}
		return result;
	}
	
	/**
	 * 알러지 목록 insert 하는 메소드
	 * @return result (Integer) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Integer insertAllergy(SignUpForm signUpForm) {
		Map<String, Object> allergyMap = new LinkedHashMap<>();
		List<String> allergyList = signUpForm.getAllergyList();
		
		int result = 0;
		for(int i = 0; i<allergyList.size(); i++) {
			allergyMap.put("familyNo", signUpForm.getFamilyNo());
			allergyMap.put("allergy", allergyList.get(i));
			result = usersMapper.insertAllergy(allergyMap);
		}
		return result;
	}


	/**
	 * userEmail을 찾아서 db select 하기
	 * @return result (Integer) 
	 * 담당자 : 빙예은
	 */
	@Override
	public Users selectByUserEmail(String userEmail) {
		log.info(userEmail);
		Users user = usersMapper.selectByUserEmail(userEmail);
		log.info("user {}", user);
		return user;
	}

	/**
	 * users에 있는 전체 데이터 가져오기
	 * @return result (Integer) 
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
	
	


}
