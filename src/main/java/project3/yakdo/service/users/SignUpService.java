package project3.yakdo.service.users;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
		Integer result = usersRepository.insertUsers(signUpForm);
		if(result == 1) {
			log.info("기본 회원가입 완료");
			return result;
		}
		log.info("기본 회원가입 실패");
		return 0;
	}
}
