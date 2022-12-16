package project3.yakdo.repository.mybatis;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project3.yakdo.repository.UsersRepository;

@Repository
@RequiredArgsConstructor
public class UsersMybatisRepository implements UsersRepository{

	private final UsersMapper usersMapper;
	
}
