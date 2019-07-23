package com.eminosoft.eazTravels.repository;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

import com.eminosoft.eazTravels.model.User;

public interface UserRepository extends CrudRepository<User,Serializable>
{

}
