package phuocthanh.services;

import phuocthanh.models.UserModel;

public interface IUserService {
	
	UserModel login(String username,String password);
	
	UserModel FindByUserName(String username);
	
	boolean register(String email,String password,String username,String fullname,String code);
	
	void updatestatus(UserModel user);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
}
