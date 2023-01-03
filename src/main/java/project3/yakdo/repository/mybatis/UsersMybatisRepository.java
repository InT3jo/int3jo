package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.SignUpForm;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsersMybatisRepository implements UsersRepository{
	
	private final UsersMapper usersMapper;
	
	@Override
	public Integer insertUsers(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsers(signUpForm);
		return result;
	}

	@Override
	public Integer insertUsersInfo(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsersInfo(signUpForm);
		return result;
	}

	@Override
	public Integer insertUsingDrugs(SignUpForm signUpForm) {
		Integer result = usersMapper.insertUsingDrugs(signUpForm);
		return result;
	}
	
	@Override
	public Integer insertAllergy(SignUpForm signUpForm) {
		Integer result = usersMapper.insertAllergy(signUpForm);
		return result;
	}


	@Override
	public Users selectByUserEmail(String userEmail) {
		log.info(userEmail);
		Users user = usersMapper.selectByUserEmail(userEmail);

		log.info("user {}", user);
		return user;
	}

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
