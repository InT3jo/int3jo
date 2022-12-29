package project3.yakdo.service.users;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
	 * 기본 정보 가입 서비스가 이뤄지는 메소드
	 * joinForm으로 받은 데이터 Users DB에 insert 실행
	 * 
	 * @param joinForm
	 * @return Integer (성공 1, 실패 0)
	 * 
	 * 담당자 : 빙예은
	 */
	public Integer signUp(JoinForm joinForm) {
		Integer result = usersRepository.insertUsers(joinForm);
		if(result == 1) {
			log.info("기본 회원가입 완료");
			return result;
		}
		log.info("기본 회원가입 실패");
		return 0;
	}
	
	/**
	 * 본인 및 가족 건강 정보 저장해두기
	 * List에 담기
	 * joinForm으로 받은 데이터 DB insert 실행
	 * 
	 * @param joinForm
	 * @return Integer
	 * 
	 * 담당자 : 빙예은
	 */
//	@Transactional
	public Integer saveInfo(JoinForm joinForm) {
		List<String> familyInfoList = new ArrayList<String>();
		
		
		Integer result = usersRepository.insertUsersInfo(joinForm);
		if(result == 1) {
			log.info("가족 정보 등록 완료");
			return result;
		}
		log.info("가족 정보 등록 실패");
		return 0;
	}

	/**
	 * 복용중인 약물 추가 서비스가 이뤄지는 메소드
	 * joinForm으로 받은 데이터 DB insert 실행
	 * 
	 * @param joinForm
	 * @return Integer
	 * 
	 * 담당자 : 빙예은
	 */
	public Integer addUsingDrugs(JoinForm joinForm) {
		Integer result = usersRepository.insertUsersUsingDrugs(joinForm);
		if(result == 1) {
			log.info("복용 중 약물 추가 완료");
			return result;
		}
		log.info("복용 중 약물 추가 실패");
		return 0;
	}

	/**
	 * 알러지 추가 서비스가 이뤄지는 메소드
	 * joinForm으로 받은 데이터 DB insert 실행
	 * 
	 * @param joinForm
	 * @return Integer
	 * 
	 * 담당자 : 빙예은
	 */
	public Integer addAllergy(JoinForm joinForm) {
		Integer result = usersRepository.insertUsersInfoAllergy(joinForm);
		if(result == 1) {
			log.info("알러지 추가 완료");
			return result;
		}
		log.info("알러지 추가 실패");
		return 0;
	}
	
	/**
	 * userInfo에
	 * userNo
	 * familyNo
	 * birth
	 * gender
	 * usingDrugs
	 * allergy
	 * weight
	 * 추가하는 메소드
	 *
	 * @return
	 
	public Integer addFamilyInfo (UsersInfo usersInfo) {
//		insert,,,userinfo에 별도로 해야하는가,,
		Integer result = usersRepository.insertFamilyInfo(usersInfo);
		
		return result;
	}
	*/
}
