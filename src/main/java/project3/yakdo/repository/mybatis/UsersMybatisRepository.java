package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsersMybatisRepository implements UsersRepository{
	
	private final UsersMapper usersMapper;
	
	@Override
	public Users insert(Users users) {
		usersMapper.insert(users);
		
		return users;
	}

	@Override
	public Users selectByUserEmail(String userEmail) {
		log.info(userEmail);
		Users user = usersMapper.selectByUserEmail(userEmail);

		log.info("user {}", user);
		return user;
	}

	@Override
	public List<Users> selectAll() {
		List<Users> userList = usersMapper.selectAll();
		
		return userList;
	}

	@Override
	public void deleteAll() {
		usersMapper.deleteAll();
	}

	
}
