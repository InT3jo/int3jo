package project3.yakdo.service.users;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.SignUpForm;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {
	private final UsersRepository usersRepository;

	/**
	 * 기본 정보 가입 서비스가 이뤄지는 메소드
	 * signForm으로 받은 데이터 Users DB에 insert 실행
	 * 유효성 검사 해야함
	 * 
	 * @param signUpForm
	 * @return Integer
	 */
	public Integer signUp(SignUpForm signUpForm) {
		//users에 signUpForm의 userEmail 전달해서 userNo 가져오기
		//users 테이블 insert
		Integer result1 = usersRepository.insertUsers(signUpForm);
		//userNo가져오기 위한 임시 users
		Users tempUsers = usersRepository.selectByUserEmail(signUpForm.getUserEmail());
		//userNo
		Integer userNo = tempUsers.getUserNo();
		//familyList의 userNo 세팅하기 위한 임시 familyList
		List<UsersInfo> tempFamilyList = signUpForm.getFamilyList();
		for(UsersInfo info : tempFamilyList) {
			info.setUserNo(userNo);
			//임시 usersInfo에 familyNo담기
			UsersInfo tempUsersInfo = usersRepository.selectFamilyNoByUserNo(userNo);
			Integer familyNo = tempUsersInfo.getFamilyNo();
			info.setFamilyNo(familyNo);
		}
		//signUpForm의 familyList에 저장
		signUpForm.setFamilyList(tempFamilyList);
		
		log.info("familyList = {}", signUpForm.getFamilyList());
		
		//users_info 테이블 insert
		Integer result2 = usersRepository.insertUsersInfo(signUpForm);
		
//		//users_info_using_drugs 테이블 insert
//		Integer result3 = usersRepository.insertUsingDrugs(signUpForm);
//		//users_info_allergy
//		Integer result4 = usersRepository.insertAllergy(signUpForm);
		
//		if(result == 1) {
//			log.info("기본 회원가입 완료");
//		return result1+result2+result3+result4;
//		}
//		log.info("기본 회원가입 실패");
		return 0;
	}
	
}
