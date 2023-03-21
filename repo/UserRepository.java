package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value="select * from user where email_id=?1",nativeQuery=true)
	User findUserByEmailIdAndRole(String emailId);
}
