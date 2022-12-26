package project3.yakdo.service.users;


import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.JoinForm;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService {
	private final UsersRepository usersRepository;
	
	/**
	 * join
	 * JoinForm을 전달 받아 날짜 변환 후 Users, UsersInfo에 저장 후
	 * Users, UsersInfo 테이블에 insert 하기
	 * 
	 * @param joinForm
	 * @return Integer (성공 1, 실패 0)
	 * 
	 * 담당자 : 빙예은 2022/12/26 10:11
	 */
	@Transactional
	public Integer join(JoinForm joinForm) {
		//joinForm으로 받아온 거 User로 전달, DB insert
		Integer result1 = insertUsers(joinForm);
		Users user = usersRepository.selectByUserEmail(joinForm.getUserEmail());
		//joinForm으로 받아온 거 UserInfo로 전달, DB insert
		Integer result2 = insertUsersInfo(joinForm,user.getUserNo());
		if(result1 + result2 == 2) {
			log.info("회원가입 완료");
			return 1;
		}
		return 0;
	}

	private Integer insertUsers(JoinForm joinForm) {
		Integer result1 = usersRepository.insertUsers(joinForm);
		return result1;
	}

	private Integer insertUsersInfo(JoinForm joinForm, Integer userNo) {
		Date birth = Date.valueOf(joinForm.getBirth());
		UsersInfo joinUserInfo = new UsersInfo(
										userNo
										, joinForm.getFamilyNo()
										, birth
										, joinForm.getGender()
										, joinForm.getUsingDrugs()
										, joinForm.getAllergy()
										, joinForm.getWeight()
									);
		log.info("userInfo: {}",joinUserInfo);
		Integer result2 = usersRepository.insertUsersInfo(joinUserInfo);
		return result2;
	}
}
