package phuocthanh.dao;

import java.util.List;

import phuocthanh.models.UserModel;

public interface IUserDao {
	
	List<UserModel> findAll();
	
	UserModel FindById(int id);
	
	void insert(UserModel user);
	
	UserModel FindByUserName(String username);
	
	void insertregister(UserModel user);
	
	void update(UserModel user);
	
	void updatestatus(UserModel user);
	
	void delate(int id);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
}
