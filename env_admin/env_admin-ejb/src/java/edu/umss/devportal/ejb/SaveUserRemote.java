package edu.umss.devportal.ejb;

import javax.ejb.Remote;

@Remote
public interface SaveUserRemote {
	
	public void createUser(String login, String name, String password);
	
}
