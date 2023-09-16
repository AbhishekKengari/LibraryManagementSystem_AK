package com.user.userDAO;
import com.dbOperations.*;
import com.model.user.User;
public class UserDAO {
	DBOperations dbOp = new DBOperations();
	
	public boolean checkUserExist(int user_id) throws Exception {
		User a =new User(user_id);
		
		return dbOp.checkUserExist(a.getUserID());
		
	}

	public void registerNewUser(String user_name)  throws Exception{
		User a =new User(user_name);
		dbOp.registerNewUser(a.getUserName());
	}

}
