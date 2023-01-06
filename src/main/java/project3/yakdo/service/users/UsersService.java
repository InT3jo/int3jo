package project3.yakdo.service.users;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;

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
	public Users compareUserNick(String userNick, Users user) {
		/**
		 * 입력 받은 닉네임이 비어 있을 때
		 * 닉네임을 입력해 주세요 를 띄워야하고
		 * 
		 * 기존 닉네임과 새로 입력받은 닉네임이 같고
		 * 업데이트가 실패했을 때
		 * 사용 중인 닉네임입니다 띄우는 validation 만들어야함
		 */
		if(usersRepository.updateUserNickByUserNo(userNick, user.getUserNo()) == 1) {
			//user 닉네임 수정
			user.setUserNick(userNick);
			return user;
		}
		
		//닉네임 수정 안 된 user
		return user;
	}

}
