package project3.yakdo.repository;

import java.util.List;

import project3.yakdo.domain.users.UsersInfo;

public interface UsersInfoRepository {

	public UsersInfo insert(UsersInfo usersInfo);
	
	public UsersInfo selectByUserNo(Integer userNo);
	
	public List<UsersInfo> selectAll();
	
	public void deleteAll();
}