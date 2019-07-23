package com.eminosoft.eazTravels.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eminosoft.eazTravels.model.User;
import com.eminosoft.eazTravels.repository.UserRepository;
import com.eminosoft.eazTravels.service.UserService;
import com.eminosoft.eazTravels.util.AppUtil;
import com.eminosoft.eazTravels.util.LoginResponse;

@Service
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false)
	public LoginResponse login(String emailId, String password) {
		LoginResponse response = new LoginResponse();

		if (validateUser(emailId, password) != 0) {

			TypedQuery < User > userHome = (TypedQuery < User > ) em
					.createNativeQuery("SELECT u.user_id,u.user_name,u.email,u.password,u.is_login FROM user u WHERE u.email='" + emailId + "'", User.class);
			User user = userHome.getSingleResult();

			TypedQuery < User > profile = (TypedQuery < User > ) em.createNativeQuery("UPDATE user SET is_login=:is_login WHERE user_id=" + user.getUserId(), User.class)
					.setParameter("is_login", true);
			int result = profile.executeUpdate();
			if (result != 0) {
				response.setUserName(user.getUserName());
				response.setEmail(user.getEmail());
				response.setMessage("You are successfully logged in");
				response.setStatus(true);
				return response;
			} else
			{
				response.setMessage("internal server error");
				return response;
			}

		}
		else {
			response.setStatus(false);
			response.setMessage("User does not exist");
			return response;
		}
	}

	@Transactional(readOnly = true)
	public int validateUser(String emailId, String password) {
		String encrptedPassword = AppUtil.sha256(password);
		try {
			Query query = em
					.createNativeQuery(
							"select user_id returnvalue from user where email=:email and password=:password").setParameter("email", emailId).setParameter("password", encrptedPassword);
			if (query.getSingleResult() != null) {
				Integer adminId = (Integer) query.getSingleResult();
				return adminId;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}
}
