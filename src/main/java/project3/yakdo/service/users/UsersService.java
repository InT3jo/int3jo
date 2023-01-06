package project3.yakdo.service.users;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.repository.UsersRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {
	
	
//	select usersNick form users where userNick = 매개변수userNick;
	
	
	
	public Integer compareUserNick(String userNick) {
		//users리스트를 가져오고
		//그 리스트 안에 파라미터로 받은 닉네임과 중복되는게 있는지 확인
		//중복이면 0
		//아니면 1
		return 1;
	}

}
