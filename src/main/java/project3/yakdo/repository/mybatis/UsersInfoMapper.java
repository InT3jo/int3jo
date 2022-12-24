package project3.yakdo.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project3.yakdo.domain.users.UsersInfo;

@Mapper
public interface UsersInfoMapper {

	public UsersInfo insert(UsersInfo usersInfo);
	
	public UsersInfo selectByUserNo(Integer userNo);
	
	public List<UsersInfo> selectAll();
	
	public void deleteAll();
}
