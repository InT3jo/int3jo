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
	 * 입력받은 userNick과 기존 loginUser의 userNick이 같은지 확인
	 * 같으면 닉네임 수정 된 loginUser return
	 * 아니면 기존 loginUser return
	 * @param userNick
	 * @return Users loginUser
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

	/**
	 * 기존 비밀 번호가 올바르게 입력 되었는지 확인 후
	 * 새 비밀번호로 변경 => 로그인 화면으로 넘어가야하기에 user를 null로 반환
	 * 비밀번호 변경에 실패했을 시 => 기존 user 반환
	 * @param passwordForm
	 * @param loginUser
	 * @return
	 */
	public Users checkModifyPw(PasswordForm passwordForm, Users loginUser) {
		/**
		 * 기존 비밀번호 맞는지 확인
		 * 새 비밀번호, 비밀번호 확인 맞는지 확인
		 * 셋 다 확인 하고 하나라도 맞지 않을 시
		 * 다시 한 번 확인해주세요 띄우기
		 * 조건 안 걸어서 무조건 null로 반환된다
		 */
		//업데이트 성공 했을 시 loginUser의 비밀번호 변경 후 로그인 하기 위해 null로 반환
		if(usersRepository.updateUserPwByUserNo(passwordForm.getUserPwNew(), loginUser.getUserNo()) == 1) {
			loginUser.setUserPw(passwordForm.getUserPwNew());
			return null;
		}
		
		//비밀번호 수정 안 됐으면 기존 loginUser return
		return loginUser;
	}

	
	/**
	 * 회원 탈퇴 실행하는 서비스
	 * 탈퇴 성공 시 return null
	 * 탈퇴 실패 시 return users
	 * @param userNo
	 * @param user 
	 * @return
	 */
	public Users leaveUser(Integer userNo, Users user) {
		if(usersRepository.updateUserStatusLeaveByUserNo(userNo) == 1) {
			return null;
		}
		return user;
	}

	
	

}
