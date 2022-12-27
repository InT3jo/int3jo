package project3.yakdo.service.users;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.JoinForm;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService {
	private final UsersRepository usersRepository;
	
	/**
	 * 회원가입 서비스가 이뤄지는 메소드
	 * joinForm으로 받은 데이터 DB insert 실행
	 * 
	 * @param joinForm
	 * @return Integer (성공 4, 실패 0)
	 * 
	 * 담당자 : 빙예은
	 */
	@Transactional
	public Integer join(JoinForm joinForm) {
		Integer result = usersRepository.insertUsers(joinForm);
		log.info("result={}", result);
		if(result == 4) {
			log.info("회원가입 완료");
			return 4;
		}
		return 0;
	}
	
}
