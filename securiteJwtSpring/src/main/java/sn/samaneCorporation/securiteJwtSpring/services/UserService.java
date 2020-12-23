package sn.samaneCorporation.securiteJwtSpring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sn.samaneCorporation.securiteJwtSpring.entities.User;
import sn.samaneCorporation.securiteJwtSpring.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User u = userRepository.findUserByUsername(username);
		
		if (u == null)
			throw  new UsernameNotFoundException(username);
		
		List<GrantedAuthority> granteds = new ArrayList<>();
		
		u.getRoles().forEach(role->{
			granteds.add( new SimpleGrantedAuthority(role.getLibelle()));
		});
		
		return new org.springframework.security.core.userdetails.User(u.toString(), u.getPassword(), granteds);
		
	}

}
