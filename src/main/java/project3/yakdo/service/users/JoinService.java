package project3.yakdo.service.users;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersInfoRepository;
import project3.yakdo.repository.UsersRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService {
	private final UsersRepository usersRepository;
	private final UsersInfoRepository usersInfoRepository;
	
	public Map<String, Object> join(Users joinUser, UsersInfo joinUserInfo) {
		Users user = usersRepository.insert(joinUser);
		UsersInfo userInfo = usersInfoRepository.insert(joinUserInfo);

		log.info("user {}", user);
		log.info("userInfo {}", userInfo);
		
		
		if(user != null && userInfo != null) {
			Map<String, Object> userInfoMap = new LinkedHashMap<>();
			userInfoMap.put("user", user);
			userInfoMap.put("userInfo", userInfo);
			
			return userInfoMap;
		}
		return null;
	}
}
