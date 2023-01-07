package project3.yakdo.service.users;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.PasswordForm;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {
	private final UsersRepository usersRepository;
	
	/**
	 * userNick과 userNo를 받아  
	 * @param userNick
	 * @return
	 */
	public Users checkModifyNick(String userNick, Users loginUser) {
		/**
		 * 입력 받은 닉네임이 비어 있을 때
		 * 닉네임을 입력해 주세요 를 띄워야하고
		 * 
		 * 기존 닉네임과 새로 입력받은 닉네임이 같고
		 * 업데이트가 실패했을 때
		 * 사용 중인 닉네임입니다 띄우는 validation 만들어야함
		 */
		//업데이트 성공 했을 시 loginUser의 닉네임 변경
		if(usersRepository.updateUserNickByUserNo(userNick, loginUser.getUserNo()) == 1) {
			//loginUser 닉네임 수정
			loginUser.setUserNick(userNick);
			return loginUser;
		}
		
		//닉네임 수정 안 된 loginUser
		return loginUser;
	}

	public Users checkModifyPw(PasswordForm passwordForm, Users loginUser) {
		/**
		 * 기존 비밀번호 맞는지 확인
		 * 새 비밀번호, 비밀번호 확인 맞는지 확인
		 * 셋 다 확인 하고 하나라도 맞지 않을 시
		 * 다시 한 번 확인해주세요 띄우기
		 */
		//업데이트 성공 했을 시 loginUser의 비밀번호 변경
		if(usersRepository.updateUserPwByUserNo(passwordForm.getUserPwNew(), loginUser.getUserNo()) == 1) {
			loginUser.setUserPw(passwordForm.getUserPwNew());
			return loginUser;
		}
		
		//비밀번호 수정 안 된 loginUser
		return loginUser;
	}

}
