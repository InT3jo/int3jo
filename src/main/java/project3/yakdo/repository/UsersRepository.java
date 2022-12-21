package project3.yakdo.repository;


import java.util.List;

import project3.yakdo.domain.users.Users;

public interface UsersRepository {

	public Users insert(Users users);
	
	public List<Users> selectAll();
	
	public void deleteAll();
}
