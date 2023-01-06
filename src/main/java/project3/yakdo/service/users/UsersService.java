package project3.yakdo.service.users;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.repository.UsersRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {
	private final UsersRepository usersRepository;
	
	/**
	 * 수정된 email을 받아 update 하기 
	 * @param userNick
	 * @return
	 */
	public Integer compareUserNick(String userNick, Integer userNo) {
		usersRepository.updateUserNickByUserNo(userNick, userNo);
		return 1;
	}

}
