package project3.yakdo.service.users;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.domain.users.UsersInfo;
import project3.yakdo.repository.UsersInfoRepository;
import project3.yakdo.repository.UsersRepository;
import project3.yakdo.validation.form.JoinForm;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinService {
	private final UsersRepository usersRepository;
	private final UsersInfoRepository usersInfoRepository;
	
	public JoinForm join(JoinForm joinForm) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		//joinForm으로 받아온 거 User로 전달
		Users joinUser = new Users(joinForm.getUserEmail()
							, joinForm.getUserPw()
							, joinForm.getUserNick()
							, LocalDateTime.now()
							, joinForm.getUserGrade()
						);
		
		LocalDateTime birth = LocalDateTime.parse(joinForm.getBirth(), formatter);
		//joinForm으로 받아온 거 UserInfo로 전달
		UsersInfo joinUserInfo = new UsersInfo(joinUser.getUserNo()
										, joinForm.getFamilyNo()
										, birth
										, joinForm.getGender()
										, joinForm.getUsingDrugs()
										, joinForm.getAllergy()
										, joinForm.getWeight()
									);
		
		
		Users user = usersRepository.insert(joinUser);
		UsersInfo userInfo = usersInfoRepository.insert(joinUserInfo);

		log.info("user {}", user);
		log.info("userInfo {}", userInfo);
		
		
		if(user != null && userInfo != null) {
			log.info("회원가입 완료");
			return joinForm;
		}
		return null;
	}
}
