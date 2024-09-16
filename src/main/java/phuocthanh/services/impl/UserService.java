package phuocthanh.services.impl;

import phuocthanh.dao.IUserDao;
import phuocthanh.dao.impl.UserDaoimpl;
import phuocthanh.models.UserModel;
import phuocthanh.services.IUserService;

public class UserService implements IUserService {

	IUserDao userDao = new UserDaoimpl();

	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	public UserModel FindByUserName(String username) {
		return userDao.FindByUserName(username);
	}

	public boolean register(String username, String password, String fullname, String email, String code) {
		if(userDao.checkExistEmail(email)) {
			return false;
		}
		if(userDao.checkExistUsername(username)) {
			return false;
		}
		userDao.insertregister(new UserModel(username,password,fullname,email,1,code,0));
		return true;
	}

	public void updatestatus(UserModel user) {
		
	}

	public boolean checkExistEmail(String email) {
		
		return false;
	}

	public boolean checkExistUsername(String username) {
		
		return false;
	}

}
