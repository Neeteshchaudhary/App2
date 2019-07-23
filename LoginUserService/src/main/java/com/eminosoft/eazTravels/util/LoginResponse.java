package com.eminosoft.eazTravels.util;

import lombok.Data;

@Data
public class LoginResponse{

	private boolean status;
	private String message;
    private String email;
    private String userName;

}

