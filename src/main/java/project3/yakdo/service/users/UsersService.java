package project3.yakdo.service.users;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {
	
	/**
	 * 수정된 email을 받아 update 하기 
	 * @param userNick
	 * @return
	 */
	public Integer compareUserNick(String userNick) {
		//users 업데이트 하기위한 코드 작성해야함
		return 1;
	}

}
