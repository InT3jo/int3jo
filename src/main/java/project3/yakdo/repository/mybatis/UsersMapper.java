package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project3.yakdo.domain.users.Users;

@Mapper
public interface UsersMapper {
	
	public Users insert(Users users);
	
	public List<Users> selectAll();
	
	public void deleteAll();

}
