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
	 * joinForm으로 받은 데이터 Users DB에 insert 실행
	 * 유효성 검사 해야함
	 * 
	 * @param joinForm
	 * @return Integer (성공 1, 실패 0)
	 * 
	 * 담당자 : 빙예은
	 */
	public Integer signUp(SignUpForm signUpForm) {
		
		//users 테이블 insert
		Integer result1 = usersRepository.insertUsers(signUpForm);
		
		//users에 signUpForm의 userEmail 전달
		Users users = usersRepository.selectByUserEmail(signUpForm.getUserEmail());
		
		//받아온 userNo 세팅
		signUpForm.setUserNo(users.getUserNo());
		log.info("userNo {}", users.getUserNo());
		
		//UsersInfo에 userNo 세팅된 signUpForm 전달
		UsersInfo usersInfo = usersRepository.selectByFamilyNo(signUpForm);
		//받아온 familyNo signUpForm familyNo에 r세팅
		signUpForm.setFamilyNo(usersInfo.getFamilyNo());
		log.info("familyNo {}", usersInfo.getFamilyNo());
		
		//users_info 테이블 insert
		Integer result2 = usersRepository.insertUsersInfo(signUpForm);
		//users_info_drugs 테이블 insert
		Integer result3 = usersRepository.insertUsingDrugs(signUpForm);
		
		
		Integer result4 = usersRepository.insertAllergy(signUpForm);
//		if(result == 1) {
//			log.info("기본 회원가입 완료");
		return result1+result2+result3+result4;
//		}
//		log.info("기본 회원가입 실패");
//		return 0;
	}
}
