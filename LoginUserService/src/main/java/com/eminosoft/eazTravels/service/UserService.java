package com.eminosoft.eazTravels.service;

import com.eminosoft.eazTravels.util.LoginResponse;

public interface UserService {

	LoginResponse login(String emailId, String password);
}
