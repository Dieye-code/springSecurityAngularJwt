package sn.samaneCorporation.securiteJwtSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.samaneCorporation.securiteJwtSpring.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findUserByUsername(String email);

}
