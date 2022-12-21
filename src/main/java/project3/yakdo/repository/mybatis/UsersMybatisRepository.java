package project3.yakdo.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project3.yakdo.domain.users.Users;
import project3.yakdo.repository.UsersRepository;

@Repository
@RequiredArgsConstructor
public class UsersMybatisRepository implements UsersRepository{
	
	private final UsersMapper memberMapper;
	
	@Override
	public Users insert(Users users) {
		memberMapper.insert(users);
		
		return users;
	}

	@Override
	public List<Users> selectAll() {
		List<Users> userList = memberMapper.selectAll();
		
		return userList;
	}

	@Override
	public void deleteAll() {
		memberMapper.deleteAll();
	}

	
}
